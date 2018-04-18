/**
 * TODO: Obtem module da aplicação para criar os controllers
 */
var dbcControllers = angular.module('dbcControllers', []);
var dbc = "dbc";

/**
 * TODO: Controller da página lista.html
 */
dbcControllers.controller('ListaCtrl', [
	'$scope',
	'$http',
	'$route',
	function($scope, $http, $route) {
		$scope.botaoSalvarAtualizar = "Salvar";
		
		$scope.init = function() {
			$http.get(dbc + "/cliente").success(
 				function(data) {
 					$scope.clientes = data;
 			});
		}
		
		/**
		 * TODO: Salva e atualiza Cliente
		 */
		$scope.salvarAtualizar = function(cliente) {
			if($scope.botaoSalvarAtualizar === "Salvar") {
				cliente = {
	 					"id" : null,
	 					"nome" : cliente.nome,
	 					"limiteCredito" : cliente.limiteCredito,
	 					"risco" : cliente.risco,
	 					"endereco" : {
	 						"uf" : cliente.endereco.uf,
	 						"cidade" : cliente.endereco.cidade,
	 						"bairro" : cliente.endereco.bairro,
	 						"logradouro" : cliente.endereco.logradouro,
	 						"numero" : cliente.endereco.numero,
	 						"complemento" : cliente.endereco.complemento,
	 						"CEP" : cliente.endereco.CEP
	 					}
	 			}
				
	 			$http.post(dbc+"/cliente", cliente).success(
	 					function(data) {
	 						alert(data);
				});
			} else {
				$http.put(dbc + "/cliente/"+cliente.id, cliente).success(
					function(data) {
						alert(data);
						
					});
			}
			$route.reload();
		}
		
		$scope.acaoExcluir = function(c) {
			$scope.clienteSelecionado = c;
		}
		
		/**
		 * TODO: Exclui cliente
		 */
		$scope.excluir = function() {
			$http.delete(dbc + "/cliente?id="+$scope.clienteSelecionado.id).success(
			function(data) {
				if(data) {
					$('#modalExcluir').on('hidden.bs.modal', function () {
						 $scope.atualizarPagina();
						})
				} else {
					alert("Erro ao excluir.")
				}
			});
		}
		
		$scope.atualizarPagina = function() {
			alert("Cliente removido com sucesso!");
			$route.reload();
		}
		
		$scope.cancelar = function() {
			delete $scope.clienteSelecionado;
		}
		
		$scope.acaoAtualizar = function(c) {
			$scope.cliente = c;
			$scope.botaoSalvarAtualizar = "Atualizar"; 
 		}
		
		$scope.limpar = function() {
			if($scope.botaoSalvarAtualizar === "Atualizar") {
				$scope.botaoSalvarAtualizar = "Salvar";
			}
			delete $scope.cliente;
		}
		
		$scope.visualizar = function(c) {
			$scope.clienteSelecionado = c;
		}
		
		/**
		 * TODO: Método chamado para obter a lista de clientes do Banco ao acessar a página
		 */
		$scope.init();
		
	} ]);