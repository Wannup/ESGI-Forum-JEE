
$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.user || {}

Esgi.module.user.Connect = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/user/connect',
             renderTo : cfg.id,
             edirect : APP_CONTEXT + '/index/',
             inputs : [
                {
                  id : 'login',
                  type : "Text",
                  name : 'login',
                  label : "Identifiant",
                  classInput : "form_el input",
                  classLabel : "form_el name",
                  emptyText : 'Saisir votre login' 
                },{
                  id : 'password',
                  type : "Password",
                  name : 'password',
                  classInput : "form_el input",
                  classLabel : "form_el pass",
                  label : "Mot de passe"
                }
             ]
        });
}

});