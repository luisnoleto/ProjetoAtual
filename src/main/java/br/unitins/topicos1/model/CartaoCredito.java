package br.unitins.topicos1.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CartaoCredito extends DefaultEntity{

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

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido idPedido;

    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public Pedido getPedido() {
        return idPedido;
    }

    public void setPedido(Pedido pedido) {
        this.idPedido = pedido;
    }


}