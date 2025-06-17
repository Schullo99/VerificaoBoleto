package br.com.unicuritiba.ProjectValidacaoBoleto.models;

import jakarta.persistence.*;

@Entity
public class Fraude {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // --- Campos padronizados para corresponder à entidade Boleto ---
    private String linhaDigitavel; // ANTES: codigoBarras
    private int codigo_banco;      // ANTES: codigoBanco (String)
    private String nome;
    private String cpf;
    private String vencimento;     // ANTES: dataValidade
    private String rua;
    private String bairro;
    private String cidade;
    private String cep;
    private String valor;

    // --- Getters e Setters ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}