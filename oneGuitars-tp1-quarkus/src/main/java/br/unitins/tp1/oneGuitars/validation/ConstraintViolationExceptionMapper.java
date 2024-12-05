package br.unitins.tp1.oneGuitars.validation;

import org.hibernate.exception.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
    ConstraintViolationError violationError = new ConstraintViolationError("400","Violação dentro do campo");
    String exc = exception.getErrorMessage();

    int initialIndexError = exc.indexOf("ERRO");
    int finalIndexError = exc.indexOf("\n",initialIndexError);
    String message = exc.substring(initialIndexError + 6,finalIndexError);

    int initialIndexDetail = exc.indexOf("Detalhe");
    int finalIndexDetail = exc.indexOf(".]",initialIndexDetail);
    String detail = exc.substring(initialIndexDetail + 9, finalIndexDetail);

    violationError.setError(message);
    violationError.setDetail(detail);

    return Response.status(Response.Status.BAD_REQUEST).entity(violationError).build();
    }
    
}
