package br.unitins.topicos1.model;

public enum TipoProduto {
    
    CD(1, "CD"),
    DVD(2, "DVD"),
    VINIL(3, "Vinil"),
    FITA_K7(4, "Fita K7");

    private int id;
    private String nome;

    private TipoProduto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
