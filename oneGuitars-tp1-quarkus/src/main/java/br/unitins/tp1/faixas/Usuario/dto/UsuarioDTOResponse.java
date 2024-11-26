package br.unitins.tp1.faixas.Usuario.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp1.faixas.Conta.model.Perfil;
import br.unitins.tp1.faixas.PessoaFisica.model.Sexo;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String cpf,
    String sexo,
    String username,
    String senha,
   List<String> perfis,
    TelefoneDTOResponse telefone
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getPessoaFisica().getNome(),
            usuario.getPessoaFisica().getCpf(),
            Sexo.valueOf(usuario.getPessoaFisica().getSexo().getId()).getDescricao(),
            usuario.getPessoaFisica().getConta().getUsername(),
            usuario.getPessoaFisica().getConta().getSenha(),
            usuario.getPessoaFisica().getConta().getPerfis().stream().map(Perfil::getDescricao).collect(Collectors.toList()), 
            TelefoneDTOResponse.valueOf(usuario.getPessoaFisica().getTelefone()
        ));
    }
}
