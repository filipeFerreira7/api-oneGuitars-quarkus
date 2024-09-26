package br.unitins.tp1.faixas.repository;

import java.util.List;

import br.unitins.tp1.faixas.model.Cidade;
import br.unitins.tp1.faixas.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade>{
    public List<Cidade> findByNome(String nome) {    
       return find("SELECT m FROM Municipio m WHERE m.nome LIKE ?1","%"+ nome +"%").list();
    }

    public List<Cidade> findByEstado(Estado estado){
        return find("SELECT m FROM Municipio m WHERE m.estado.id = ?1", estado.getId()).list();
    }
}
