package br.unitins.tp1.faixas.Usuario.repository;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{

    public List<Usuario> findByNome(String nomePessoaFisica) {  
        
        if(nomePessoaFisica == null){
            return null;
        }
        return find("FROM usuario WHERE UNACCENT(UPPER(pessoaFisica.nome)) LIKE UNACCENT(?1)",
        "%"+ nomePessoaFisica.toUpperCase() + "%").list();
    }

}
