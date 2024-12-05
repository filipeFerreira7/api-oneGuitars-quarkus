package br.unitins.tp1.oneGuitars.Endereco.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.oneGuitars.Endereco.dto.EnderecoDTOResponse;
import br.unitins.tp1.oneGuitars.Endereco.model.Endereco;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EnderecoService {

    Endereco findById(Long id);

     Endereco findByCep(String cep);

    List<EnderecoDTOResponse> findAll();

    EnderecoDTOResponse create(EnderecoDTORequest dto);

    EnderecoDTOResponse update(Long id, EnderecoDTORequest dto);

    void delete(Long id);
}
