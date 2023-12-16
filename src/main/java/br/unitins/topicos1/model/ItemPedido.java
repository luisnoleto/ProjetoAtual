package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity {

    private Integer quantidade;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album; 

    public ItemPedido(Album album, Integer quantidade) {
        this.album = album;
        this.quantidade = quantidade;
    }

    public ItemPedido() {
        super();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
