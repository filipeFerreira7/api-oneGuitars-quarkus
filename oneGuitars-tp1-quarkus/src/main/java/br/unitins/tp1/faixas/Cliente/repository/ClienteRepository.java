package br.unitins.tp1.faixas.Cliente.repository;

import java.util.List;

import br.unitins.tp1.faixas.Cliente.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{

    public List<Cliente> findByNome(String nome) {
        return find("UPPER(pessoaFisica.nome) LIKE ?1",
            "%" + nome.toUpperCase() + "%").list();
    }

    
    public Cliente findByCpf(String cpf){
    return find("SELECT c FROM Cliente c WHERE c.pessoaFisica.cpf = ?1",cpf).firstResult();

    }
    public Cliente findByIdUsuario(Long idUsuario){
        return find("SELECT c FROM Cliente c WHERE c.pessoaFisica.usuario.id = ?1", idUsuario).firstResult();
    }
        public Cliente findByUsernameAndSenha(String username, String senha){
            return find("SELECT c FROM Cliente c WHERE c.pessoaFisica.usuario.username = ?1 AND pessoaFisica.usuario.senha = ?2", username, senha).firstResult();
    }

}
