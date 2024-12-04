package br.unitins.tp1.faixas.Usuario.repository;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{

    public List<Usuario> findByNome(String nome) {
        return find("UPPER(pessoaFisica.nome) LIKE ?1",
            "%" + nome.toUpperCase() + "%").list();
    }

    
    public Usuario findByCpf(String cpf){
    return find("SELECT u FROM Usuario u WHERE u.pessoaFisica.cpf = ?1",cpf).firstResult();

    }
    public Usuario findByIdUsuario(Long idUsuario){
        return find("SELECT u FROM Usuario u WHERE pessoaFisica.conta.id = ?1", idUsuario).firstResult();
    }
        public Usuario findByUsernameAndSenha(String username, String senha){
            return find("SELECT u FROM Usuario u WHERE pessoaFisica.conta.username = ?1 AND pessoaFisica.conta.senha = ?2", username, senha).firstResult();
    }

     public Usuario findByUsername(String username){
        return find("SELECT u FROM Usuario u WHERE u.pessoaFisica.conta.username = ?1", username).firstResult();
}

}
