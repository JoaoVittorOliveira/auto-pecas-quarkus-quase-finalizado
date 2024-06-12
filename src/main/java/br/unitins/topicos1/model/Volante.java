package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Volante extends Produto {
    @Column
    private String diametro;

    @ManyToOne 
    @JoinColumn(name = "idCor")
    private Cor cor;

    @ManyToOne 
    @JoinColumn(name = "idFormato")
    private Formato formato;

    @ManyToOne 
    @JoinColumn(name = "idMaterial")
    private Material mateiral;

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public Material getMateiral() {
        return mateiral;
    }

    public void setMateiral(Material mateiral) {
        this.mateiral = mateiral;
    }
}
