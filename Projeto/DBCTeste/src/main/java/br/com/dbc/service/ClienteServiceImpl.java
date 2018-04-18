package br.com.dbc.service;

import java.util.List;
import java.util.Optional;
import br.com.dbc.cliente.DAO.ClienteDAOImpl;
import br.com.dbc.cliente.DAO.IClienteDAO;
import br.com.dbc.cliente.model.Cliente;

public class ClienteServiceImpl implements IClienteService {
	private IClienteDAO clienteDAO = new ClienteDAOImpl();
	
	public Cliente salvar(Cliente cliente) {
		return clienteDAO.salvar(cliente);
	}

	public Cliente atualizar(Cliente cliente) {
		return clienteDAO.atualizar(cliente);
	}

	public List<Cliente> listar() {
		return clienteDAO.listar();
	}

	public boolean remover(Cliente cliente) {
		return clienteDAO.remover(cliente);
	}

	public Optional<Cliente> obterPorId(Long id) {
		return clienteDAO.obterPorId(id);
	}

}
