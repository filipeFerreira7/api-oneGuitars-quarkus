package br.unitins.tp1.faixas.form;

import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class PessoaFisicaImageForm {

    @FormParam("nomeImagem")
    private String nomeImagem;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
