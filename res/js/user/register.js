$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.user || {}

Esgi.module.user.Register = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/user/Register',
             renderTo : cfg.id,
             inputs : [
                {
                  type : "Text",
                  name : 'fname',
                  label : "Nom",
                },{
                  type : "Text",
                  name : 'lname',
                  label : "Prenom", 
                },{
                  type : "Text",
                  name : 'login',
                  label : "Identifiant",
                },{
                  type : "Password",
                  name : 'password',
                  label : "Mot de passe",
                },{
                  type : "Password",
                  name : 'passwordVerification',
                  label : "Mot de passe verification",
                }
             ]
        });
}

});
