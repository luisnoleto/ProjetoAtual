package br.unitins.topicos1.model;


import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Album extends DefaultEntity{


    @Size(min = 4 , max = 4, message = "O ano de lançamento deve ter 4 caracteres")
    @Column(length = 4)
    private String anoLancamento;  

    @NotBlank(message = "O nome deve ser informado")
    private String nome;
    
    private String descricao;

    @NotNull
    private Double preco;

    private Integer estoque;

    private LocalDate dataCadastro;

    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_gravadora", nullable = false)
    private Gravadora gravadora;

    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @Enumerated(EnumType.STRING)
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = LocalDate.now();
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
}

