package br.unitins.topicos1.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Pix extends DefaultEntity {

    private Double valor;;

    private String chaveAleatoria;

    private LocalDate dataExpiracaoTokenPix;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido idPedido;

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

    public String getChaveAleatoria() {
        return chaveAleatoria;
    }

    public void setChaveAleatoria(String chaveAleatoria) {
        this.chaveAleatoria = chaveAleatoria;
    }

    public LocalDate getDataExpiracaoTokenPix() {
        return dataExpiracaoTokenPix;
    }

    public void setDataExpiracaoTokenPix(LocalDate dataExpiracaoTokenPix) {
        this.dataExpiracaoTokenPix = dataExpiracaoTokenPix;
    }
}
