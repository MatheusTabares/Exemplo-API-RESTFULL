package br.com.dbc.service;

import java.util.List;
import java.util.Optional;
import br.com.dbc.cliente.model.Cliente;

public interface IClienteService {
	public Cliente salvar(Cliente cliente);
	public Cliente atualizar(Cliente cliente);
	public List<Cliente> listar();
	public boolean remover(Cliente cliente);
	public Optional<Cliente> obterPorId(Long id);
}
