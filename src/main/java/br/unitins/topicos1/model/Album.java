package br.unitins.topicos1.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Album extends Produto{


    @Size(min = 4 , max = 4, message = "O ano de lançamento deve ter 4 caracteres")
    @Column(length = 4)
    private String anoLancamento;  

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_gravadora", nullable = false)
    private Gravadora gravadora;

    @OneToMany
    @JoinColumn(name = "id_genero")
    private List<Genero> genero = new ArrayList<Genero>();

    private TipoProduto tipoProduto;

    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    public Gravadora getGravadora() {
        return gravadora;
    }
    public void setGravadora(Gravadora gravadora) {
        this.gravadora = gravadora;
    }	
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public String getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public List<Genero> getGenero() {
        return genero;
    }

    public void setGenero(List<Genero> genero) {
        this.genero = genero;
    }

}
