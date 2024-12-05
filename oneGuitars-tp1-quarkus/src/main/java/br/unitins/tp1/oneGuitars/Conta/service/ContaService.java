package br.unitins.tp1.oneGuitars.Conta.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Conta.model.Conta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ContaService {

    Conta findById(Long id);

    Conta findByUsernameAndSenha(String username, String senha);

    public void update(Conta conta);

    List<Conta> findByNome(String nome);

    Conta findByCpf(String cpf);

    List<Conta> findAll();
}
