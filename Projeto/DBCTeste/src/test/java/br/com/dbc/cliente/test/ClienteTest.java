package br.com.dbc.cliente.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import br.com.dbc.cliente.DAO.IClienteDAO;
import br.com.dbc.cliente.DAO.ClienteDAOImpl;
import br.com.dbc.cliente.model.Cliente;
import br.com.dbc.cliente.model.Endereco;

public class ClienteTest {
	private Cliente cliente;
	private Endereco endereco;
	private IClienteDAO clienteDAO = new ClienteDAOImpl();
	
	@Test
	public void testSalvar() {
		endereco = new Endereco("RS", "Porto Alegre", "Hípica", "Rua do Scheneider", 1516, "casa 65",  "91789-790");
		cliente = new Cliente("Matheus", 1000, "A", endereco);
		Cliente clienteRetornado = clienteDAO.salvar(cliente);
		
		assertEquals(cliente, clienteRetornado);
	}
	
	@Test
	public void testCalcularJurosComRiscoA() {
		Integer jurosEsperado = null;
		cliente = new Cliente();
		cliente.setRisco(JurosRiscoEnumTest.A.toString());
		Integer jurosRetornado = cliente.getJuros() != null ? cliente.getJuros() : null;
		assertEquals(jurosEsperado, jurosRetornado);
	}
	
	@Test
	public void testCalcularJurosComRiscoB() {
		Integer jurosEsperado = 10;
		cliente = new Cliente();
		cliente.setRisco(JurosRiscoEnumTest.B.toString());
		Integer jurosRetornado = cliente.getJuros() != null ? cliente.getJuros() : null;
		assertEquals(jurosEsperado, jurosRetornado);
	}
	
	@Test
	public void testCalcularJurosComRiscoC() {
		Integer jurosEsperado = 20;
		cliente = new Cliente();
		cliente.setRisco(JurosRiscoEnumTest.C.toString());
		Integer jurosRetornado = cliente.getJuros() != null ? cliente.getJuros() : null;
		assertEquals(jurosEsperado, jurosRetornado);
	}
	
	public void testAtualizar() {
		clienteDAO = new ClienteDAOImpl();

		endereco = new Endereco("RS", "POA", "Restinga", "Acesso E", 2333, "", "91790-100");
		cliente = new Cliente("Joao", 1000, "A", endereco);
	    clienteDAO.salvar(cliente);
		
		cliente.getEndereco().setUf("SC");
		cliente.getEndereco().setCidade("Florianópolis");
		cliente.getEndereco().setBairro("Barra da Lagoa");
		cliente.getEndereco().setLogradouro("Rua A");
		cliente.getEndereco().setNumero(1010);
		cliente.getEndereco().setComplemento("Próximo a Praia da Prainha");
		cliente.getEndereco().setCEP("91790-111");
		cliente.setNome("Joao Da Silva");
		cliente.setLimiteCredito(1001);
		cliente.setRisco("B");
		
		Cliente clienteAtualizado = clienteDAO.atualizar(cliente);
		
		assertEquals(cliente, clienteAtualizado);
	}
	
	@Test
	public void testListar() {
		clienteDAO = new ClienteDAOImpl();
		List<Cliente> listaPreInclusao = clienteDAO.listar();
		
		endereco = new Endereco("RS", "Canoas", "Guajuviras", "Acesso B", 1234, "", "91690-200");
		cliente = new Cliente("Maria", 900, "B", endereco);
	    clienteDAO.salvar(cliente);
		
	    List<Cliente> listaAposInclusao = clienteDAO.listar();
	    
	    assertEquals(listaAposInclusao.size() - 1, listaPreInclusao.size());
	}
	
	@Test
	public void testRemover() {
		clienteDAO = new ClienteDAOImpl();
		List<Cliente> listaPreRemocao = clienteDAO.listar();
		
		endereco = new Endereco("RS", "Cachoeirinha", "Guajuviras", "Acesso B", 1234, "", "91690-200");
		cliente = new Cliente("Jones", 1100, "B", endereco);
		Cliente clienteSalvo = clienteDAO.salvar(cliente);

		clienteDAO.remover(clienteSalvo);
		List<Cliente> listaPosRemocao = clienteDAO.listar();
		
		assertEquals(listaPosRemocao.size(), listaPreRemocao.size());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testObterPorId() {
		clienteDAO = new ClienteDAOImpl();
		Optional<Cliente> clienteObtido = Optional.empty();
		
		endereco = new Endereco("RS", "Guaiba", "Passo das Pedras", "Acesso B", 1234, "", "91690-200");
		cliente = new Cliente("Davi", 1100, "C", endereco);
		Cliente clienteSalvo = clienteDAO.salvar(cliente);

		if(clienteSalvo.getId() != null) {
			clienteObtido.ofNullable(clienteDAO.obterPorId(clienteSalvo.getId()));
		}
		if(clienteObtido.isPresent()) {
			assertEquals(clienteObtido, clienteSalvo);
		}
	}
}
