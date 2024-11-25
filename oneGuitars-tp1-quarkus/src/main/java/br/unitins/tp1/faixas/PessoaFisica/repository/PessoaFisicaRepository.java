package br.unitins.tp1.faixas.PessoaFisica.repository;

import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica>{
     

}
