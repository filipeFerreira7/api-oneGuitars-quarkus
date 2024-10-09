package br.unitins.tp1.faixas.repository;

import java.util.List;

import br.unitins.tp1.faixas.model.Especificacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class EspecificacaoRepository implements PanacheRepository<Especificacao>{

    public List<Especificacao> findBySku(String sku) {    
        return find("SELECT e FROM Especificacao e WHERE e.sku LIKE ?1", "%" + sku + "%").list();
    }

    // Método para buscar especificações pelo ID de uma outra especificação (provavelmente relacionado ao estado ou outra entidade)
    public List<Especificacao> findByEspecificacao(Especificacao especificacao){
        return find("SELECT e FROM Especificacao e WHERE e.id = ?1", especificacao.getId()).list();
    }
}
