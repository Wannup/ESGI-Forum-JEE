$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.user || {}

Esgi.module.user.Register = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/user/register',
             renderTo : cfg.id,
             redirect : APP_CONTEXT + '/index/',
             inputs : [
                {
                  type : "Text",
                  id : 'fname',
                  name : 'fname',
                  label : "Nom",
                },{
                  type : "Text",
                  name : 'lname',
                  id : 'lname',
                  label : "Prenom", 
                },{
                  type : "Text",
                  name : 'email',
                  id : 'email',
                  label : "E-Mail", 
                },{
	              id : 'login',
	              type : "Text",
	              name : 'login',
	              label : "Identifiant",
	              emptyText : 'Saisir votre login' 
	            },{
	              id : 'password',
	              type : "Password",
	              name : 'password',
	              label : "Mot de passe"
	            },{
                  type : "Password",
                  name : 'passwordVerification',
                  id : 'passwordVerification',
                  label : "Mot de passe verification",
                }
             ]
        });
}

});
