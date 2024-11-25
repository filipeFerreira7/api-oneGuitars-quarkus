package br.unitins.tp1.faixas.Administrador.service;

import java.util.List;

import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTORequest;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTOResponse;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorPasswordUpdateDTO;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorUsernameUpdateDTO;
import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

public interface AdministradorService {
    
    public AdministradorDTOResponse create(@Valid AdministradorDTORequest dto);

    public void update(Long id, AdministradorDTORequest dto) throws ValidationException;
    
    public void updateContaPassword(AdministradorPasswordUpdateDTO passwordUpdateDTO);
    
    public void updateContaUsername(AdministradorUsernameUpdateDTO usernameUpdateDTO);
    
    public boolean delete(Long id);
    
    public List<AdministradorDTOResponse> findAll();
    
    public AdministradorDTOResponse findById(Long id);
    
    public List<AdministradorDTOResponse> findByNome(String nome);
    
    public AdministradorDTOResponse findByUsername(String username);
    
    public AdministradorDTOResponse findByCpf(String cpf);
   
    public ContaDTOResponse login(String username, String hashSenha);
}