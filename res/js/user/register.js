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
                  emptyText : 'Saisir votre nom' 
                },{
                  type : "Text",
                  name : 'lname',
                  emptyText : 'Saisir votre prénom' 
                },{
                  type : "Text",
                  name : 'login',
                  emptyText : 'Saisir votre login' 
                },{
                  type : "Password",
                  name : 'password',
                },{
                  type : "Password",
                  name : 'passwordVerification',
                }
             ]
        });
}

});
