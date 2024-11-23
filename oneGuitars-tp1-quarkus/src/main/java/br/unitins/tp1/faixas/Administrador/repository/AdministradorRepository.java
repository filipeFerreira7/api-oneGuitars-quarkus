package br.unitins.tp1.faixas.Administrador.repository;

import java.util.List;

import br.unitins.tp1.faixas.Administrador.model.Administrador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador> {
    
    public List<Administrador> findByNome(String nome){
        return find("UPPER(pessoaFisica.nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Administrador findByCpf(String cpf){
        return find("pessoaFisica.cpf = ?1", cpf).firstResult();
    }

    public Administrador findByUsername(String username){
        return find("pessoaFisica.usuario.username = ?1", username).firstResult();
    }

    public Administrador findByUsernameAndSenha(String username, String senha){
        return find("pessoaFisica.usuario.username = ?1 AND pessoaFisica.usuario.password = ?2", username, senha).firstResult();
    }

    public Administrador findByIdUsuario(Long idUsuario) {
        return find("pessoaFisica.usuario.id = ?1", idUsuario).firstResult();
    }
    
}