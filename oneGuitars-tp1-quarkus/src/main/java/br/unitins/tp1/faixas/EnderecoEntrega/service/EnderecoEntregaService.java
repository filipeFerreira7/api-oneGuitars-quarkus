package br.unitins.tp1.faixas.EnderecoEntrega.service;

import java.util.List;

import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTORequest;
import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTOResponse;
import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EnderecoEntregaService {

    EnderecoEntrega findById(Long id);

     EnderecoEntrega findByCep(String cep);

    List<EnderecoEntregaDTOResponse> findAll();

    EnderecoEntregaDTOResponse create(EnderecoEntregaDTORequest dto);

    EnderecoEntregaDTOResponse update(Long id, EnderecoEntregaDTORequest dto);

    void delete(Long id);
}
