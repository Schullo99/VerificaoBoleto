package br.com.unicuritiba.ProjectValidacaoBoleto.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String nome;
	private String CNPJ;
	private int codigo_verificacao;
	private int cont_fraudes;
	private boolean isInBlacklist; // Novo campo para blacklist

	@ManyToOne
	private Cliente cliente;
	
	@OneToMany
	private List<Boleto> boletos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo_verificacao() {
		return codigo_verificacao;
	}
	public void setCodigo_verificacao(int codigo_verificacao) {
		this.codigo_verificacao = codigo_verificacao;
		
	}
	public int getCont_fraudes() {
		return cont_fraudes;
	}
	public void setCont_fraudes(int cont_fraudes) {
		this.cont_fraudes = cont_fraudes;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public boolean isInBlacklist() {
		return isInBlacklist;
	}
	public void setInBlacklist(boolean isInBlacklist) {
		this.isInBlacklist = isInBlacklist;
	}
}