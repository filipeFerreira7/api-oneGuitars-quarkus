package br.unitins.tp1.faixas.Usuario.repository;

import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica>{
    
}
