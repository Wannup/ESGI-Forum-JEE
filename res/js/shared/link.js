$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.shared = Esgi.module.shared || {}

	Esgi.module.shared.link = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/index',
			renderTo : cfg.id,
			label : ' Accueil '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/register/',
			renderTo : cfg.id,
			label : ' Inscription '
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/connect',
			renderTo : cfg.id,
			label : 'Connexion'
		});
	}

});