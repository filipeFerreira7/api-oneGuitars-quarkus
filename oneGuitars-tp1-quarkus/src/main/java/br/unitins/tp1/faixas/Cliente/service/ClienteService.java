package br.unitins.tp1.faixas.Cliente.service;
import java.util.List;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.dto.ClienteDTOResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClienteService {

    ClienteDTOResponse findById(Long id);

    ClienteDTOResponse findByCpf(String cpf);

    List<ClienteDTOResponse> findByNome(String nome);

    List<ClienteDTOResponse> findAll();

    ClienteDTOResponse create(ClienteDTORequest dto);

    ClienteDTOResponse update(Long id, ClienteDTORequest dto);

    void delete(Long id);



}
