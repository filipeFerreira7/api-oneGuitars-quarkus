package br.unitins.tp1.faixas.Usuario.repository;

import java.util.Collections;
import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{

    public List<Usuario> findByNome(String nomePessoaFisica) {
        if (nomePessoaFisica == null || nomePessoaFisica.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se o nome for nulo ou vazio
        }
        return find("FROM Usuario WHERE UPPER(pessoaFisica.nome) LIKE ?1",
            "%" + nomePessoaFisica.toUpperCase() + "%").list();
    }

    
    public Usuario findByCpf(String cpf){
    return find("pessoaFisica.cpf = ?1",cpf).firstResult();

    }

    public Usuario findByUsernameAndSenha(String username, String senha){
             return find("SELECT u FROM Usuario u WHERE u.username = ?1 AND u.senha =?2",
             username,senha).firstResult();
    }
    }


