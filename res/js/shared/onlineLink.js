$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.shared = Esgi.module.shared || {}

	Esgi.module.shared.onlineLink = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/index',
			renderTo : cfg.id,
			label : ' Accueil '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/userpage',
			renderTo : cfg.id,
			label : ' Espace perso '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/deconnect/',
			renderTo : cfg.id,
			label : ' Deconnexion ',		
		});

	}

});