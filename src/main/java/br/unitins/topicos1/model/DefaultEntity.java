package br.unitins.topicos1.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class DefaultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAlteracao;

    @PrePersist
    public void registrarDataCadastro() {
        setDataCadastro(LocalDateTime.now());
    }

    @PreUpdate
    public void registrarDataAlteracao() {
        setDataAlteracao(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}