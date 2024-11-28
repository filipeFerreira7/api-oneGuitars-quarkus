package br.unitins.tp1.faixas.validation;

import org.jboss.logging.Logger;

import br.unitins.tp1.faixas.Usuario.resource.UsuarioResource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
 private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Log.error("Bean Validation Error: "+exception.getMessage());
       ValidationError error = new ValidationError("400", "Erro de Validação");

       for(ConstraintViolation<?> violation : exception.getConstraintViolations()){
        String fullFieldName = violation.getPropertyPath().toString();
        int index = fullFieldName.lastIndexOf(".");
        String fieldName = fullFieldName.substring(index+1);
        String message = violation.getMessage();

        error.addFieldError(fieldName, message);
       }
       return Response.status(400).entity(error).build();
    }
    
}
