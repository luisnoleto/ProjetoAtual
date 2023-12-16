package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioFileService implements FileService {

    private final String PATH_USER = System.getProperty("user.home") +
        File.separator + "quarkus" +
        File.separator + "images" +
        File.separator + "usuario" +  File.separator;
    
    private static final List<String> SUPPORTED_MIME_TYPES = 
        Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; 

    @Override
    public String salvar(String nomeImagem, byte[] imagem) throws IOException {
        verificarTamanhoImagem(imagem);

        verificarTipoImagem(nomeImagem);

        Path diretorio = Paths.get(PATH_USER);
        Files.createDirectories(diretorio);

        Path filePath = diretorio.resolve(nomeImagem);

        if (filePath.toFile().exists()) 
            throw new IOException("Imagem com esse nome já existe.");

   
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(imagem);
        }

        return filePath.toFile().getName();
    }

    @Override
    public File download(String nomeImagem) {
        File file = new File(PATH_USER+nomeImagem);
        return file;
    }

    private void verificarTamanhoImagem(byte[] imagem) throws IOException {
        if (imagem.length > MAX_FILE_SIZE) 
            throw new IOException("Imagem maior que 10mb.");
    }

    private void verificarTipoImagem(String nomeImagem) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeImagem));
        if (!SUPPORTED_MIME_TYPES.contains(mimeType)) 
            throw new IOException("Tipo de imagem não suportada.");
  
    }

   
    private final String PATH_ALBUM = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "album" + File.separator;

    @Override
    public String salvarProduto(Long idAlbum, String nomeImagem, byte[] imagem) throws IOException{

        File diretorio = new File(PATH_ALBUM + idAlbum);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        String path = PATH_ALBUM + idAlbum + File.separator + nomeImagem;

        File file = new File(path);

        if (file.exists()) {
            throw new IOException("O nome gerado da imagem está repetido.");
        }

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(imagem);

        fos.flush();
        fos.close();

        return path;
    }

    @Override
    public File downloadProduto(Long idAlbum, String nomeImagem) {
       

        return new File(PATH_ALBUM + idAlbum + File.separator + nomeImagem);
    }
}

    
