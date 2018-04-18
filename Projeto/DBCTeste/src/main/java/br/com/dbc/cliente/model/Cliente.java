package br.com.dbc.cliente.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private float limiteCredito;
	private String risco;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	private Integer juros;
	
	public Cliente() {}
	
	public Cliente(String nome, float limiteCredito, String risco, Endereco endereco) {
		this.nome = nome;
		this.limiteCredito = limiteCredito;
		setRisco(risco); 
		this.endereco = endereco;
	}
	
	/**
	 * TODO : Método para calcular Taxa de Juros do Cliente
	 * 
	 */
	private void calcularJuros(String risco){
		if(risco.equals("A")) { 
			this.setJuros(null);
		} else if(risco.equals("B")) {
			this.setJuros(10);
		} else {
			this.setJuros(20);
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public String getRisco() {
		return risco;
	}
	public void setRisco(String risco) {
		this.risco = risco;
		calcularJuros(risco);
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Integer getJuros() {
		return juros;
	}	
	public void setJuros(Integer juros) {
		this.juros = juros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((juros == null) ? 0 : juros.hashCode());
		result = prime * result + Float.floatToIntBits(limiteCredito);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((risco == null) ? 0 : risco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (juros == null) {
			if (other.juros != null)
				return false;
		} else if (!juros.equals(other.juros))
			return false;
		if (Float.floatToIntBits(limiteCredito) != Float.floatToIntBits(other.limiteCredito))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (risco == null) {
			if (other.risco != null)
				return false;
		} else if (!risco.equals(other.risco))
			return false;
		return true;
	}
	
}
