package br.unitins.topicos1.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    private String salt = "TOPICOS1";

    private Integer interationCount = 405;

    private Integer keyLength = 512;
    
    @Override
    public String getHashSenha(String senha) {
        
            try {
                byte[] result =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret( new PBEKeySpec(senha.toCharArray(), salt.getBytes(), interationCount, keyLength))
                .getEncoded();

                return Base64.getEncoder().encodeToString(result);
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao gerar hash da senha");
            }
                
           
    }
public static void main(String[] args) {
    HashServiceImpl hash = new HashServiceImpl();
    System.out.println(hash.getHashSenha("123"));
    System.out.println(hash.getHashSenha("123"));
    System.out.println(hash.getHashSenha("124"));

    }
}
