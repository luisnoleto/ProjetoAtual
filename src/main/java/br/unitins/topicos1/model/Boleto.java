package br.unitins.topicos1.model;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Boleto extends DefaultEntity{

    private String numeroBoleto;

    public Double valor;

    @Column(nullable = false)
    private LocalDateTime dataGeracaoBoleto;

    @Column(nullable = false)
    private LocalDateTime dataVencimento;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido idPedido;

    public LocalDateTime getDataGeracaoBoleto() {
        return dataGeracaoBoleto;
    }

    public void setDataGeracaoBoleto(LocalDateTime dataGeracaoBoleto) {
        this.dataGeracaoBoleto = dataGeracaoBoleto;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(String numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;

    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;

    }
}
