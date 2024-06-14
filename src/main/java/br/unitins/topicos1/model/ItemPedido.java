package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity{

    private Double valor;

    @Column(name = "porcentagem_desconto")
    private Double porcentagemDesconto;

    @Column(name = "quantidade_produtos")
    private Integer quantidadeProdutos;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public void setQuantidadeProdutos(Integer quantidadeProdutos) {
        this.quantidadeProdutos = quantidadeProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Double porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
    
}