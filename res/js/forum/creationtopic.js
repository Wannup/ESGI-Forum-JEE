
$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.forum || {}

Esgi.module.forum.CreationTopic = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/forum/creationtopic',
             renderTo : cfg.id,
             redirect : APP_CONTEXT + '/forum/topicmessagelist/',
             inputs : [
                {
                  id : 'sujet',
                  type : "Text",
                  name : 'sujet',
                  label : "Titre du sujet",
                  emptyText : 'Sujet' 
                }
             ]
        });
}

});