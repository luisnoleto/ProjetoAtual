package br.unitins.topicos1.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CartaoCredito extends Pagamento {

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeImpressoCartao;

    @Column(nullable = false)
    private String cpfTitular;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private String codigoSeguranca;

 

    public CartaoCredito(Double valor, String numeroCartao, String nomeImpressoCartao,
            String cpfTitular, LocalDate dataValidade, String codigoSeguranca) {

        super(valor);

        this.numeroCartao = numeroCartao;
        this.nomeImpressoCartao = nomeImpressoCartao;
        this.cpfTitular = cpfTitular;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
     
    }

    public CartaoCredito() {

    }

    public String getNumeroDoCartao() {
        return numeroCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroCartao = numeroDoCartao;
    }

    public String getNomeImpressoCartao() {
        return nomeImpressoCartao;
    }

    public void setNomeImpressoCartao(String nomeImpressoCartao) {
        this.nomeImpressoCartao = nomeImpressoCartao;
    }

    public String getCpfDoTitular() {
        return cpfTitular;
    }

    public void setCpfDoTitular(String cpfDoTitular) {
        this.cpfTitular = cpfDoTitular;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }



}