
$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.user || {}

Esgi.module.user.Connect = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/user/connect',
             renderTo : cfg.id,
             redirect : APP_CONTEXT + '/index/',
             inputs : [
                {
                  id : 'login',
                  type : "Text",
                  name : 'login',
                  label : "Identifiant",
                  emptyText : 'Login' 
                },{
                  id : 'password',
                  type : "Password",
                  name : 'password',
                  label : "Mot de passe"
                }
             ]
        });
}

});