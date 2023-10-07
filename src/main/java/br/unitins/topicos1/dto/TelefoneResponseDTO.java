 
 package br.unitins.topicos1.dto;
 import br.unitins.topicos1.model.Telefone;


public record TelefoneResponseDTO(
    String codigoArea,
    String numero
    
) {
   public static TelefoneDTO valeuOf(Telefone telefone){
        return new TelefoneDTO(
            telefone.getCodigoArea(), 
            telefone.getNumero());
    }
 }

