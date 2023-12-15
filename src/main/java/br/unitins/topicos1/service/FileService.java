package br.unitins.topicos1.service;
import java.io.File;
import java.io.IOException;

public interface FileService {
    
    String salvar(String nomeImagem, byte[] imagem) throws IOException;

    String salvarProduto(Long id, String nomeImagem, byte[] imagem) throws IOException;

    File download(String nomeArquivo); 

    File downloadProduto(Long idAlbum, String nomeArquivo);

}
