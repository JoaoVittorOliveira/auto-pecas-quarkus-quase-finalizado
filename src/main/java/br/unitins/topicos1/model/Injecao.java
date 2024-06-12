package br.unitins.topicos1.model;

import jakarta.persistence.*;

@Entity
public class Injecao extends Produto {
    
    @Column
    private String tipoCombustivel;

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

}
