package br.unitins.tp1.faixas.Administrador.service;

import java.util.List;

import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTORequest;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTOResponse;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorPasswordUpdateDTO;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorUsernameUpdateDTO;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

public interface AdministradorService {
    
    public AdministradorDTOResponse create(@Valid AdministradorDTORequest dto);
    public void update(Long id, AdministradorDTORequest dto) throws ValidationException;
    public void updateUsuarioPassword(AdministradorPasswordUpdateDTO passwordUpdateDTO);
    public void updateUsuarioUsername(AdministradorUsernameUpdateDTO usernameUpdateDTO);
    public boolean delete(Long id);
    public List<AdministradorDTOResponse> findAll();
    public AdministradorDTOResponse findById(Long id);
    public List<AdministradorDTOResponse> findByNome(String nome);
    public AdministradorDTOResponse findByUsername(String username);
    public AdministradorDTOResponse findByCpf(String cpf);
    public UsuarioDTOResponse login(String username, String hashSenha);
}