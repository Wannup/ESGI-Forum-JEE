
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
                },   {
                    id : 'commentaire',
                    type : "Text",
                    name : 'commentaire',
                    label : "commentaire",
                    emptyText : 'commentaire' 
                  },
                  {
                      id : 'sujet',
                      type : "Text",
                      name : 'sujet',
                      label : "Titre du sujet",
                      emptyText : 'Sujet' 
                    },  {
                        id : 'titre',
                        type : "Text",
                        name : 'titre',
                        label : "titre",
                        emptyText : 'titre' 
                      }
             ]
        });
}

});