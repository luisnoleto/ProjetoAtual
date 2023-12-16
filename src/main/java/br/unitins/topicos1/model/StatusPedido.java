package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"),
    PAGO(2, "Pago"),
    ENVIADA(3,"Enviada"),
    CANCELADA(4,"Cancelada"),
    ENTREGUE(5,"Entregue");

    private final Integer id;
    private final String label;

    
    private StatusPedido(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (StatusPedido status : StatusPedido.values()) {
            if (status.getId().equals(id))
                return status;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
    
}
