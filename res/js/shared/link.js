$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.shared = Esgi.module.shared || {}

	Esgi.module.shared.MenuPrincipal = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/index',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : ' Accueil '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/userpage',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : ' Espace perso '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/connect',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Connexion'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/register/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : ' Inscription '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/deconnect/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : ' Deconnexion '
		});

	}

});