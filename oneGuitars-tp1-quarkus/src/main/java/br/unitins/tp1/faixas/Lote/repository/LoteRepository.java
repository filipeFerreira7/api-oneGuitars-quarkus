package br.unitins.tp1.faixas.Lote.repository;


import br.unitins.tp1.faixas.Lote.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {

// Tudo que tem haver com bd

public Lote findByCodigo(String cod){
    return find("SELECT l FROM Lote l WHERE l.codigo = ?1",cod).firstResult();
}

/**
 * @return retorna a faixa com o lote mais antigo e com qtd > 0 
 */
public Lote findByIdGuitarra(Long idGuitarra){
    StringBuffer jpql = new StringBuffer();

    jpql.append("SELECT");
    jpql.append("l");
    jpql.append("FROM");
    jpql.append("Lote l");
    jpql.append("WHERE");
    jpql.append("l.faixa.id = ?1");
    jpql.append("AND l.estoque>0");
    jpql.append("ORDER BY l.data");
    return find(jpql.toString(), idGuitarra).firstResult();
}

}


