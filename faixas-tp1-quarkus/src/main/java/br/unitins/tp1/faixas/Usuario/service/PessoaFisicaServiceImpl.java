package br.unitins.tp1.faixas.Usuario.service;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Sexo;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;


@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    @Inject
    public PessoaFisicaRepository pessoafisicaRepository;

    @Inject
    Validator validator;

    @Override
    public PessoaFisica insertPessoaFisica(PessoaFisicaDTORequest pessoaFisicaDTORequest) {
      
      PessoaFisica pF = new PessoaFisica();

      pF.setNome(pessoaFisicaDTORequest.nome());
      pF.setCpf(pessoaFisicaDTORequest.cpf());
      pF.setEmail(pessoaFisicaDTORequest.email());
      pF.setSexo(Sexo.valueOf(pessoaFisicaDTORequest.idSexo()));

      pessoafisicaRepository.persist(pF);

      return pF;
    }




    @Override
    public void updatePessoaFisica(Usuario usuario, PessoaFisicaDTORequest dto) {
      Long idPessoaFisica = usuario.getPessoaFisica().getId();

      usuario.setPessoaFisica(insertPessoaFisica(dto));

      pessoafisicaRepository.deleteById(idPessoaFisica);
    }



}