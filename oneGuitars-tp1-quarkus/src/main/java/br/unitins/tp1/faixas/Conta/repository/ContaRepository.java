package br.unitins.tp1.faixas.Conta.repository;

import java.util.Collections;
import java.util.List;

import br.unitins.tp1.faixas.Conta.model.Conta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class ContaRepository implements PanacheRepository<Conta>{

    public List<Conta> findByNome(String nomePessoaFisica) {
        if (nomePessoaFisica == null || nomePessoaFisica.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se o nome for nulo ou vazio
        }
        return find("FROM Conta WHERE UPPER(pessoaFisica.nome) LIKE ?1",
            "%" + nomePessoaFisica.toUpperCase() + "%").list();
    }

    
    public Conta findByCpf(String cpf){
    return find("pessoaFisica.cpf = ?1",cpf).firstResult();

    }

    public Conta findByUsernameAndSenha(String username, String senha){
             return find("SELECT c FROM Conta u WHERE c.username = ?1 AND c.senha =?2",
             username,senha).firstResult();
    }

    public Conta findByUsername(String username){
        return find("SELECT u FROM Usuario u WHERE u.username = ?1",
        username).firstResult();
}
    }


