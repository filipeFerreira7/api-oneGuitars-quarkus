package br.unitins.tp1.faixas.Usuario.resource;

import java.io.IOException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp1.faixas.Conta.repository.ContaRepository;
import br.unitins.tp1.faixas.Conta.service.ContaService;
import br.unitins.tp1.faixas.File.service.FileService;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.PasswordUpdateDTO;
import br.unitins.tp1.faixas.Usuario.dto.UsernameUpdateDTO;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import br.unitins.tp1.faixas.form.PessoaFisicaImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    ContaService contaService;

    @Inject
    ContaRepository contaRepository;

    @Inject
    public FileService pessoafisicaFileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(usuarioService.findById(id)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return Response.ok(usuarioService.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(usuarioService.findByNome(nome)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(usuarioService.findAll()).build();

    }

    @POST
    public Response create(@Valid UsuarioDTORequest dto) {
        usuarioService.create(dto);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid UsuarioDTORequest dto) {
        usuarioService.update(id, dto);
        return Response.noContent().build();
    }

     @PATCH
    @RolesAllowed("User")
    @Path("/update-password")
    public Response updateContaPassword(PasswordUpdateDTO passwordUpdateDTO){
        usuarioService.updatePassword(passwordUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("User")
    @Path("/update-username")
    public Response updateContaUsername(UsernameUpdateDTO usernameUpdateDTO){
        usuarioService.updateUsername(usernameUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm PessoaFisicaImageForm form) {
        try {
            String nomeImagem = pessoafisicaFileService.save(form.getNomeImagem(), form.getImagem());

            usuarioService.updateNomeImagem(id, nomeImagem);

        } catch (IOException e) {
            Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(pessoafisicaFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename= " + nomeImagem);
        return response.build();

    }

}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)