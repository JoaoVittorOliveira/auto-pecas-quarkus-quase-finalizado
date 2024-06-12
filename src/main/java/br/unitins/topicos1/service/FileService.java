package br.unitins.topicos1.service;

import java.io.File;

public interface FileService {
    
    void upload(Long id, String nomeImage, byte[] imagem);
    File download(String nomeImagem);
}
