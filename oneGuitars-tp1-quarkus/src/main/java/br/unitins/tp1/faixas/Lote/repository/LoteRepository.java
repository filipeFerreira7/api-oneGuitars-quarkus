package br.unitins.tp1.faixas.Lote.repository;


import java.util.List;

import br.unitins.tp1.faixas.Lote.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {

// Tudo que tem haver com bd

public List<Lote> findByCodigo(String cod){
    return find("SELECT l FROM Lote l WHERE l.codigo LIKE ?1","%"+cod+"%").list();
}


}


