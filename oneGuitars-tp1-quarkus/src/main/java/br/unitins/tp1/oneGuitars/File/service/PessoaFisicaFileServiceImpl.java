package br.unitins.tp1.oneGuitars.File.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.jboss.logging.Logger;

import br.unitins.tp1.oneGuitars.Usuario.resource.UsuarioResource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaFileServiceImpl implements FileService {
    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    private final String PATH_USUARIO = System.getProperty("user.home") +
            File.separator + "quarkus" +
            File.separator + "images" +
            File.separator + "usuario" + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 mb

    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
        if (arquivo == null || arquivo.length == 0) {
            throw new IOException("Arquivo enviado está vazio");
        }

        verificarTamanhoArquivo(arquivo);
        Tika tika = new Tika();
        LOG.info("Detectando tipo MIME para o arquivo: "+nomeArquivo);
        String mimeType = tika.detect(arquivo);
        LOG.info("Tipo MIME detectado: {} " + mimeType);
        if (mimeType == null || !SUPPORTED_MIME_TYPES.contains(mimeType)) {
            throw new IOException("Formato de arquivo inválido ou não suportado");
        }

        Path dir = Paths.get(PATH_USUARIO);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        String extensao = mimeType.substring(mimeType.lastIndexOf("/") + 1);
      
        String novoNomeArquivo = nomeArquivo + "." + extensao;

          //caminho final do arquivo
        Path filePath = dir.resolve(novoNomeArquivo);
        existeArquivo(novoNomeArquivo, dir);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }
        return novoNomeArquivo;
    }

    private void verificarTamanhoArquivo(byte[] arq) throws IOException {
        if (arq.length > MAX_FILE_SIZE) {
            throw new IOException("O tamanho do arquivo ultrapassa os 10MB limite");
        }
    }

    private String existeArquivo(String nome, Path dir) {
        String novoNome = UUID.randomUUID().toString();
        Path filePath = dir.resolve(novoNome);

        while (filePath.toFile().exists()) {
            novoNome = UUID.randomUUID().toString();
            LOG.info("O nome foi alterado pois ja existia um arquivo com este nome: " + novoNome);
            filePath = dir.resolve(novoNome);
        }
        return novoNome;
    }

    @Override
    public File find(String nomeArquivo) {
        // eh ideal verificar se existe para nao retornar um file vazio
        nomeArquivo.split(nomeArquivo);
        File file = new File(PATH_USUARIO + nomeArquivo);

        if (!file.exists())
            throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo, new FileNotFoundException());

        return file;
    }

}