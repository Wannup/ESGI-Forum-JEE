package org.esgi.orm.my;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.annotations.ORM_TABLE;
import org.esgi.orm.my.annotations.ORM_TYPE_INTEGER;
import org.esgi.orm.my.annotations.ORM_TYPE_VARCHAR;


public class ORM implements IORM {

	static ORM instance;
	static String mysqlHost;
	static String mysqlDatabase;
	static String mysqlUser;
	static String mysqlPassword;
	
	static {
		instance = new ORM();
		mysqlHost = "localhost";
		mysqlDatabase = "esgi";
		mysqlUser = "root";
		mysqlPassword = "";
	}
	
	public static Object save(Object o) {
		return instance._save(o);
	}

	public static Object load(Class<?> c, Object id) {
		return instance._load(c, id);
	}

	public static boolean remove(Class<?> c, Object id) {
		return instance._remove(c, id);
	}
	
	public static String createConnectionString(){
		return "jdbc:mysql://" +mysqlHost + "/" + mysqlDatabase;
	}
	
	public static Object select(Class c, ORM_SEARCH searchColumn) {
		return instance._select(c, searchColumn);
	}
	
	public static Object loadAllTable(Class c) {
		return instance._loadAllTable(c);
	}
	
	public static Connection createConnectionObject(){
		Connection connection;
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		try { 
			connection = DriverManager.getConnection(
					createConnectionString(),
					mysqlUser,
					mysqlPassword);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return connection;
	}
	
	public static ArrayList<Object[]> getAllField(Class clazz){

		Field tabTempPropriete[] = clazz.getFields();

		ArrayList<Object[]> tabFields = new ArrayList<>();

		for(int i = 0 ; i < tabTempPropriete.length ; i++){  
			if(tabTempPropriete[i].getModifiers() == Modifier.PUBLIC){
				Object[] tabF = new Object[3];
				tabF[0] = (Object) tabTempPropriete[i].getGenericType();
				tabF[1] =  namePackageToNamePropertie(tabTempPropriete[i].toString());
				if(tabTempPropriete[i].getAnnotation(ORM_PK.class) instanceof ORM_PK){
					tabF[2] = "PRIMARY_KEY";


				}else{
					tabF[2] = "";
				}
				tabFields.add(tabF);
			}
		}


		return tabFields;
	}
	
	public static String namePackageToNamePropertie(String namePackage){
		String nameClasse[] =namePackage.split("\\.");
		return nameClasse[nameClasse.length-1];
	}
	
	@Override
	public Object _load(Class<?> c, Object id) {
		Object o = null;
		
		//get a connection object
		Connection connection = createConnectionObject();
		
		//get the id 
		String parsedId = id.toString();
		
		//build the query
		String tableName = getTableName(c);
		StringBuilder query = new StringBuilder();
		query.append("SELECT * from "+tableName+" WHERE id=?;");
		
		//build the result
		try {
			//instantiates an empty returnable object
			o = c.newInstance();
			 
			//send the query
			PreparedStatement ps = connection.prepareStatement(query.toString());
			ps.setString(1, parsedId);
			ResultSet result = ps.executeQuery();
			result.beforeFirst();
			result.next();
			
			//parse the result fields
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int i=1; i<=columnCount; i++){
				System.out.println(result.getString(i));
				int valueType = rsmd.getColumnType(i);
				Field f = c.getField(rsmd.getColumnName(i));
				
				if(valueType == Types.INTEGER){
					f.setInt(o, result.getInt(i));
					continue;
				}

				f.set(o, result.getString(i));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public boolean _remove(Class<?> c, Object id) {
		return false;
	}

	@Override
	public Object _save(Object o) {
		if(null == o){
			System.out.println("Warning, object to save is a nullptr");
			return null;
		}
		
		Connection connection = createConnectionObject();
		if(connection == null){
			System.out.println("Invalid Connection object");
			return null;
		}
		
		Hashtable<String, Object> persistentFields = getFields(o);
		
		try { 
			connection = DriverManager.getConnection(
					createConnectionString(),
					mysqlUser,
					mysqlPassword);
			
			Class<?> c = o.getClass();
			StringBuilder query = new StringBuilder();
			String tableName = "";
			if(null != c.getAnnotation(ORM_TABLE.class)){
				tableName = c.getAnnotation(ORM_TABLE.class).value();
			}else{
				tableName = c.getSimpleName().toLowerCase();
			}
			
			/** Try to create the table **/
			query.append("CREATE TABLE `" +mysqlDatabase+"` . `"+tableName+"` ( ");
			
			Field fields[] = c.getFields();
			for(Field f : fields){
				//The field must be a persistent data
				if(Modifier.isVolatile(f.getModifiers())) continue;
				
				//Add the field name
				query.append("`"+f.getName()+"` ");
				
				//Get the field type
				Class<?> fieldType = f.getType();
				
				//Add the corresponding db field type to the query
				if(null != f.getAnnotation(ORM_TYPE_INTEGER.class)){
					//INT type was specified
					query.append("INT (" + f.getAnnotation(ORM_TYPE_INTEGER.class).value() + ") ");
				}
				
				else if(null != f.getAnnotation(ORM_TYPE_VARCHAR.class)){
					//VARCHAR type was specified
					query.append("VARCHAR (" + f.getAnnotation(ORM_TYPE_VARCHAR.class).value() + ") ");
				}
				
				else if(null != f.getAnnotation(ORM_PK.class)){
					query.append("INT (10) AUTO_INCREMENT PRIMARY KEY ");
				}
				
				else if(
						(fieldType.equals(int.class))
					 || (fieldType.equals(Integer.class))
					 || (fieldType.equals(long.class))
					 || (fieldType.equals(Long.class))
				){
					query.append("INT (10) ");
				}
				
				else if(
					    (fieldType.equals(float.class))
					 || (fieldType.equals(Float.class))
					 || (fieldType.equals(double.class))
					 || (fieldType.equals(Double.class))
				){
					query.append("FLOAT (10) ");
				}
				
				else{
					query.append("TEXT ");
				}
				
				query.append("NOT NULL ");
				
				query.append(",");
			}
			query.replace(query.lastIndexOf(","), query.length(), "");
			query.append(" )");
			
			System.out.println(query.toString());
			Statement st = connection.createStatement();
			try{
				st.execute(query.toString());
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			st.close();
			
			/** Try to update the object **/
			
			//The object needs an integer ID field 
			int id = -1; 
			for(Field f : fields){
				if(f.getName().toLowerCase().equals("id")){
					try {
						Object value = f.get(o);
						String valueStr = value.toString();
						id = Integer.parseInt(valueStr);
						
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						break;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						System.out.println("Exception on accessing field " + f.getName() 
								+ ". Check if it is public and accessible.");
						break;
					} catch (NullPointerException e){
						id = -1;
					}
				}
			}
			
			query = new StringBuilder();
			
			if(id == -1){

				System.out.println("ID not set. The save will be an INSERT");
				query.append("INSERT INTO `"+tableName+"` (");
				
				StringBuilder fieldNames = new StringBuilder();
				StringBuilder fieldDots = new StringBuilder();
				ArrayList<String> fieldValues = new ArrayList<>();
				
				for(Map.Entry<String,Object> field : persistentFields.entrySet()){
					if(field.getKey().toLowerCase().equals("id")) continue;
					fieldNames.append(field.getKey() + ",");
					fieldDots.append("?,");
					fieldValues.add(field.getValue().toString());
				}
				
				fieldNames.replace(fieldNames.lastIndexOf(","), fieldNames.length(), "");
				fieldDots.replace(fieldDots.lastIndexOf(","), fieldNames.length(), "");
				
				query.append(fieldNames);
				query.append(") VALUES (");
				query.append(fieldDots);
				query.append(")");
				PreparedStatement insertSt = connection.prepareStatement(query.toString());
				
				int insertStColumn = 1;
				for(String s : fieldValues){
					insertSt.setString(insertStColumn++, s);
				}
				
				System.out.println(insertSt);
				insertSt.execute();
				
			}else{
				
				System.out.println("ID found: "+id+" The save will be an UPSERT");
				query.append("SELECT count(id) FROM "+tableName+" WHERE id=?");
				PreparedStatement selectSt = connection.prepareStatement(query.toString());
				selectSt.setInt(1, id);
				ResultSet rs = selectSt.executeQuery();
				rs.beforeFirst();
				rs.next();
				
				int count = rs.getInt(1);
				if(count == 0){
					//INSERT
					query = new StringBuilder();
					query.append("INSERT INTO `"+tableName+"` (");
					for(Map.Entry<String,Object> field : persistentFields.entrySet()){
						query.append(field.getKey() + ",");
					}
					
					query.replace(query.lastIndexOf(","), query.length(), "");
					query.append(") VALUES (");
					
					for(int i=0; i<persistentFields.size(); i++){
						query.append("?,");
					}
					
					query.replace(query.lastIndexOf(","), query.length(), "");
					query.append(")");
					PreparedStatement insertSt = connection.prepareStatement(query.toString());
					
					int queryField = 1;
					for(Map.Entry<String,Object> field : persistentFields.entrySet()){
						insertSt.setString(queryField++, field.getValue().toString());
					}
					System.out.println(insertSt);
					insertSt.execute();
					
				}else{
					//UPDATE
					query = new StringBuilder();
					query.append("UPDATE " + tableName + " SET ");
					
					for(Map.Entry<String,Object> field : persistentFields.entrySet()){
						if(field.getKey().toLowerCase().equals("id")) continue;
						query.append(field.getKey() + "=" + "?, ");
					}
					
					query.replace(query.lastIndexOf(","), query.length(), "");
					query.append(" WHERE id="+id);
					PreparedStatement updateSt = connection.prepareStatement(query.toString());
					
					int updateStColumn = 1;
					for(Map.Entry<String,Object> field : persistentFields.entrySet()){
						if(field.getKey().toLowerCase().equals("id")) continue;
						updateSt.setString(updateStColumn++, field.getValue().toString());
					}
					
					updateSt.execute();
				}
			}
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return o;
	}
	
	/**
	 * Returns a map of persistent fields (no volatile fields) of an object.
	 * Those are the fields saved in the dtabase.
	 * @param o The object to analyse
	 * @return The corresponding fields, keys are field name, values are field's value
	 */
	private static Hashtable<String, Object> getFields(Object o){
		Hashtable<String, Object> fields = new Hashtable<>();
		
		Field rawFields[] = o.getClass().getFields();
		for(Field f : rawFields){
			//The field must be a persistent data
			if(Modifier.isVolatile(f.getModifiers())) continue;
			
			String fieldName = f.getName();
			Object fieldValue = null;
			
			try {
				fieldValue = f.get(o);
			} 
			
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				break;
			}
			
			catch (IllegalAccessException e) {
				e.printStackTrace();
				System.out.println("Exception on accessing field " + f.getName() 
						+ ". Check if it is public and accessible.");
				break;
			} 
			
			catch (NullPointerException e){
				e.printStackTrace();
			}
			
			finally{
				if(null == fieldValue) fieldValue = new String();
				fields.put(fieldName, fieldValue);
			}
		}
		
		return fields;
	}
	
	private static String getTableName(Class<?> c){
		String tableName = "";
		if(null != c.getAnnotation(ORM_TABLE.class)){
			tableName = c.getAnnotation(ORM_TABLE.class).value();
		}else{
			tableName = c.getSimpleName().toLowerCase();
		}
		return tableName;
	}
	
	public static void mapValue(Object o, Field f, Object value){
		if(null==o || null==f || null== value) return;		
		try{
			f.set(o, value);
		}
		catch(Exception e){
			System.out.println("Couldn't map value to object field");
			e.printStackTrace();
		}
	}
	
	public static void mapValue(Object o, Field f, Object value, int type){
		if(null==o || null==f || null== value) return;		
		try{
			if(type == Types.INTEGER){
				f.setInt(o, (int) value); 
			}
			
			else if(type == Types.CHAR){
				f.setChar(o, (char) value); 
			}
			
			else if(type == Types.FLOAT){
				f.setFloat(o, (float) value); 
			}
			
			else{
				mapValue(o, f, value);
			}
		}

		catch(Exception e){
			System.out.println("Couldn't map value to object field");
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Object> _loadAllTable(Class c) {
		Connection co = instance.createConnectionObject();
		ArrayList<Object> list =  new ArrayList<>();
		String nameTab = getTableName(c);
		String sql = "SELECT * FROM "+ nameTab +";";
		
		try {
			PreparedStatement stat = (PreparedStatement) co.prepareStatement(sql) ;
			ResultSet rs = (ResultSet) stat.executeQuery () ;

			
			Object tempO;
			Field[] fields = c.getFields();
			while (rs.next ())
			{
				tempO = c.newInstance();
				for(Field f : fields){
					if(f.getModifiers() == 1){
						String nameField = namePackageToNamePropertie(f.getName());
						f.set(tempO, rs.getObject(nameField));
					}
				}
				list.add(tempO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ArrayList<Object> _select(Class c, ORM_SEARCH searchColumn) {
		ArrayList<Object> list =  new ArrayList<>();
		Connection co = instance.createConnectionObject();
		String champs = "";
		String nameTab = getTableName(c);
		ArrayList<Object[]> tabChamps = getAllField(c);
		
		try {			
			
			ArrayList<String> tabPK = new ArrayList<String>();

			for(Object[] chps : tabChamps){
				champs += chps[1] + ",";
				if(chps[2].toString().equals("PRIMARY_KEY"))
					tabPK.add(chps[1].toString());
			}
			champs = champs.substring(0,champs.length()-1);
				
			String sql = "SELECT " + champs +" FROM "+ nameTab +" WHERE ";
			int i=0;
			
			ArrayList<String> tempValue = new ArrayList<>();
			
			for(Entry<String, String> entry : searchColumn.getRecherche().entrySet()) {
			    String cle = entry.getKey();
			    tempValue.add(entry.getValue());
			    
			    if(i>0){
			    	sql += " AND " + cle + " = ? ";
			    }else{
			    	sql += " " + cle + " = ? ";
			    }
			    i++;
			}
						
			PreparedStatement stat = (PreparedStatement) co.prepareStatement(sql) ;		
			
			for(i = 0 ; i<tempValue.size() ; i++){
				stat.setString(i+1, tempValue.get(i));
			}
			
			ResultSet rs = (ResultSet) stat.executeQuery () ;
		
			Object temp;
			Field[] fields = c.getFields();
			while (rs.next ())
			{
				temp = c.newInstance();
				for(Field f : fields){
					if(f.getModifiers() == 1){
						String nameField = namePackageToNamePropertie(f.getName());
						f.set(temp, rs.getObject(nameField));
					}
				}
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}







