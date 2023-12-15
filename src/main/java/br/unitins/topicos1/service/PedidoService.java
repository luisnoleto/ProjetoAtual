package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.dto.ItemPedidoDTO;

public interface PedidoService {

    List<PedidoResponseDTO> getAll (Long idUsuario);
    
    PedidoResponseDTO insert (Long idUsuario, ItemPedidoDTO itemPedidoDTO);

    void delete (Long idUsuario, Long idItemPedido);

    void efetuarPagamentoBoleto(Long idUsuario);

    void efetuarPagamentoPix(Long idUsuario);

    void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO);

    void finalizarPedido (Long idPedido);
}