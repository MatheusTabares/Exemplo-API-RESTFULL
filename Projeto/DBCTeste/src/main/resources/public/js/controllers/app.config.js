/**
 * TODO: Inicia o modulo AngularJS mapeado no index.html com a diretiva ng-app na tag <html>
 * e configura o mapeamento das rotas com seus respectivos Controllers.
 */
angular.module('dbcModule', [ 'ngRoute', 'dbcControllers', 'dbcServices' ])
		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/lista', {
				templateUrl : '../../view/cliente/lista.html',
				controller : 'ListaCtrl'
			})
			
			.otherwise({
				redirectTo : '/lista'
			});
		} ]);