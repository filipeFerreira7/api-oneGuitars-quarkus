package br.unitins.tp1.oneGuitars.Pagamento.repository;

import br.unitins.tp1.oneGuitars.Pagamento.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

}
