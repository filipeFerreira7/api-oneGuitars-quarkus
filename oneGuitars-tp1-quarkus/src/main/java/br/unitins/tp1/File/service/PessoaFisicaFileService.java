package br.unitins.tp1.File.service;

import java.io.File;
import java.io.IOException;

public class PessoaFisicaFileService implements FileService {

    private final PATH_PESSOA_FISICA;

    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
     return "Filipe";
    }

    @Override
    public File find(String nomeArquivo) {
        return File.createTempFile(nomeArquivo, nomeArquivo);
    }

    
    
}
