package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Formato extends DefaultEntity {
    @Column
    private String descricaoFormato;

    public String getDescricaoFormato() {
        return descricaoFormato;
    }

    public void setDescricaoFormato(String descricaoFormato) {
        this.descricaoFormato = descricaoFormato;
    }
}
