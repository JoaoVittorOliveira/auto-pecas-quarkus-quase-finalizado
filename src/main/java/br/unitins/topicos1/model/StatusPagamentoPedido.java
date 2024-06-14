package br.unitins.topicos1.model;

public enum StatusPagamentoPedido {
    
    NAO_PAGO(1, "Pedido Não Pago"),
    PAGO(2, "Pedido Pago");

    private int id;
    private String descricao;

    StatusPagamentoPedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static StatusPagamentoPedido valueOf(int id) throws IllegalArgumentException{
        for(StatusPagamentoPedido statusPagamentoPedido : StatusPagamentoPedido.values()){
            if(statusPagamentoPedido.getId() == id)
                return statusPagamentoPedido;
        }
        throw new IllegalArgumentException("id de tamanhoCano invalido");
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
