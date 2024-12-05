package br.unitins.tp1.oneGuitars.Estado.service;

// 
import java.util.List;

import br.unitins.tp1.oneGuitars.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.oneGuitars.Estado.model.Estado;
import br.unitins.tp1.oneGuitars.Estado.repository.EstadoRepository;
import br.unitins.tp1.oneGuitars.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

  @Inject
  public EstadoRepository estadoRepository;

  @Override
  public Estado findById(Long id) {
    return estadoRepository.findById(id);
  }

  @Override
  public List<Estado> findByNome(String nome) {
    return estadoRepository.findByNome(nome);
  }

  @Override
  public List<Estado> findAll() {
    return estadoRepository.findAll().list();
  }

  @Override
  @Transactional
  public Estado create(EstadoDTORequest dto) {
    validarSigla(dto.sigla());

    Estado estado = new Estado();
    estado.setNome(dto.nome());
    estado.setSigla(dto.sigla());
    estadoRepository.persist(estado);
    return estado;
  }

  private void validarSigla(String sigla) {
    Estado estado = estadoRepository.findBySigla(sigla);
    if (estado != null)
      throw new ValidationException("sigla", "Esta sigla ja foi utilizada por outro estado");

  }

  @Override
  @Transactional
  public Estado update(long id, EstadoDTORequest dto) {
    Estado estado = estadoRepository.findById(id);
    estado.setNome(dto.nome());
    estado.setSigla(dto.sigla());

    estadoRepository.persist(estado);
    return estado;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    estadoRepository.deleteById(id);
  }

}
