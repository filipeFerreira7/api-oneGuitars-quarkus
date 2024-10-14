package br.unitins.tp1.faixas.Usuario.service;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface PessoaFisicaService {

    PessoaFisica findById(Long id);

    List<PessoaFisica> findByNome(String nome);
    
    List<PessoaFisica> findAll();

    PessoaFisica create(PessoaFisicaDTORequest dto); 
    
    PessoaFisica update(Long id, PessoaFisicaDTORequest dto);

    void delete(Long id);
}
