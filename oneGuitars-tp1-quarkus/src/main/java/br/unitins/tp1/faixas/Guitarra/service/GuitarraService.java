package br.unitins.tp1.faixas.Guitarra.service;

import java.util.List;

import br.unitins.tp1.faixas.Guitarra.dto.GuitarraDTORequest;
import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface GuitarraService {

    Guitarra findById(Long id);

    List<Guitarra> findByNome(String nome);

    List<Guitarra> findAll();

    Guitarra create(@Valid GuitarraDTORequest dto);

    Guitarra update(Long id, GuitarraDTORequest dto);

    Guitarra updatePreco(Long id, Double novoPreco);

    void delete(Long id);
}
