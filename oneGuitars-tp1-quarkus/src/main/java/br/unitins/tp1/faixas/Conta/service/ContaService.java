package br.unitins.tp1.faixas.Conta.service;

import java.util.List;

import br.unitins.tp1.faixas.Conta.model.Conta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ContaService {

    Conta findById(Long id);

    Conta findByUsernameAndSenha(String username, String senha);

    public void update(Conta conta);

    List<Conta> findByNome(String nome);

    List<Conta> findByCpf(String cpf);

    List<Conta> findAll();

    void delete(Long id);
}
