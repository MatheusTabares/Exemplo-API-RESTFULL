package br.com.dbc.cliente.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import br.com.dbc.cliente.model.Cliente;
import br.com.dbc.cliente.util.JPAUtil;

public class ClienteDAOImpl implements IClienteDAO {
	
	private EntityManager em;
	
	/**
	 * TODO: Construtor para iniciar conexao com Banco de dados.
	 */
	public ClienteDAOImpl() {
    	em = JPAUtil.getEntityManager();
    }

	public Cliente salvar(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return cliente;
	}
	
	public Cliente atualizar(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.merge(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return cliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		return em.createQuery("FROM Cliente").getResultList();
	}
	
	public boolean remover(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
		return true;
	}
	
	public Optional<Cliente> obterPorId(Long id) {
		return Optional.ofNullable(em.find(Cliente.class, id));
	}
}
