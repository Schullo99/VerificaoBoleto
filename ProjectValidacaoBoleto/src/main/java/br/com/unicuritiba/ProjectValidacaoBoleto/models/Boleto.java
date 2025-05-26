package br.com.unicuritiba.ProjectValidacaoBoleto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity

public class Boleto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private int codigo_barras;
	private int codigo_banco;
	private String nome;
	private String CPF;
	private String vencimento;
	private String Rua;
	private String Bairro;
	private String Cidade;
	private String CEP;
	private double valor;
	
	@ManyToOne
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCodigo_barras() {
		return codigo_barras;
	}
	public void setCodigo_barras(int codigo_barras) {
		this.codigo_barras = codigo_barras;
	}
	public int getCodigo_banco() {
		return codigo_banco;
	}
	public void setCodigo_banco(int codigo_banco) {
		this.codigo_banco = codigo_banco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = CPF;
	}

	public String getRua() {
		return Rua;
	}
	public void setRua(String rua) {
		Rua = rua;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	
	
}
