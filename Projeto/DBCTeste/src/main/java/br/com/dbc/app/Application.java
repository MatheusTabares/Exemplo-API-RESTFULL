package br.com.dbc.app;

import static spark.Spark.*;

import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;
import br.com.dbc.cliente.model.Cliente;
import br.com.dbc.service.ClienteServiceImpl;
import br.com.dbc.service.IClienteService;

public class Application {
	
	private static final String dbc = "dbc";
	
	public static void main(String[] args) throws Exception {
		
		Gson gson = new Gson();
		staticFileLocation("/public");
		
		IClienteService clienteService = new ClienteServiceImpl();
		
		/**
		 * TODO : Serviço para MANTER Cliente
		 */
		
		post(dbc + "/cliente", (req, res) -> {
			String body = req.body();
			Cliente cliente = parseClienteFromBody(body); 
			if(cliente != null) {
				clienteService.salvar(cliente);
				return gson.toJson("Cliente salvo com sucesso!");
			}
			res.status(404);
			return "Erro ao salvar um cliente.";
		});
		
		get(dbc + "/cliente", (req, res) -> {
			List<Cliente> clientes = clienteService.listar();
			return clientes.size() > 0 ? 
					gson.toJson(clientes) : "Lista vazia";
		});
		
		delete(dbc + "/cliente", (req, res) -> {
			Long id = Long.parseLong(req.queryParams("id"));
			Optional<Cliente> cliente = clienteService.obterPorId(id);
			if(cliente.isPresent()) {
				return clienteService.remover(cliente.get());
			} else {
				res.status(404);
				return false;
			}
		});
		
		put(dbc + "/cliente/:id", (req, res) -> {
			Long id = Long.parseLong(req.params(":id"));
			Optional<Cliente> clienteDB = clienteService.obterPorId(id);
			if (clienteDB.isPresent()) {
				String body = req.body();	
				Cliente clienteAtualizado = parseClienteFromBody(body);
				clienteService.atualizar(clienteAtualizado);
				
				return gson.toJson("Cliente atualizado");
			} else {
				res.status(404);
				return "Erro ao atualizar cliente";
			}
		});
		
	}
	
	private static Cliente parseClienteFromBody(String body) {
		Gson gson = new Gson();
		return gson.fromJson(body, Cliente.class);
	}
}
