package br.unitins.tp1.faixas.Endereco.service;

import java.util.List;

import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTOResponse;
import br.unitins.tp1.faixas.Endereco.model.Endereco;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EnderecoService {
   
    Endereco findyById(Long id);

    List<EnderecoDTOResponse> findByCep(String cep);

    List<EnderecoDTOResponse> findAll();

    EnderecoDTOResponse create(EnderecoDTORequest dto);

    EnderecoDTOResponse update(Long id, EnderecoDTORequest dto);

    void delete(Long id);
}
