
$(function(){
Esgi.module = Esgi.module || {}
Esgi.module.user = Esgi.module.forum || {}

Esgi.module.forum.CreationMessage = function (cfg) {
        new Esgi.html.Form({
             url : APP_CONTEXT+'/forum/creationmessage',
             renderTo : cfg.id,
             redirect : APP_CONTEXT + '/forum/topicmessagelist/',
             inputs : [
                {
                  id : 'message',
                  type : "Text",
                  name : 'message',
                  label : "Message",
                  emptyText : 'Message' 
                }
             ]
        });
}

});