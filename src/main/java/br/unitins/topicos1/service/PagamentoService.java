package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.PedidoResponseDTO;

public interface PagamentoService {
    
    public PedidoResponseDTO realizarPagamento(String login, Long id);



    
}
