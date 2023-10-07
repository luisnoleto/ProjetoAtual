package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario extends DefaultEntity {

    private String login;
    private String nome;

    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
    private Boolean isAdmin;

    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true )
    @JoinTable(name="usuario_telefone",
        joinColumns= @JoinColumn(name="id_usuario"),
        inverseJoinColumns = @JoinColumn(name="id_telefone") )
    private List<Telefone> listaTelefone;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getIsAdmin(){
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    
    
}
