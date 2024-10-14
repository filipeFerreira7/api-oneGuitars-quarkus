package br.unitins.tp1.faixas.Usuario.repository;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica>{

    public List<PessoaFisica> findByNome(String nome) {    
       return find("SELECT p FROM PessoaFisica p WHERE p.nome LIKE ?1","%"+ nome +"%").list();
    }

}
