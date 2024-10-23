package br.unitins.tp1.faixas.Usuario.service;

import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface PessoaFisicaService {

    PessoaFisica insertPessoaFisica (PessoaFisicaDTORequest pessoaFisicaDTORequest);


    void updatePessoaFisica (Usuario usuario, PessoaFisicaDTORequest PessoaFisicaDTORequest);


}
