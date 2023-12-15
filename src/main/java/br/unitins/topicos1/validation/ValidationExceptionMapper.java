package br.unitins.topicos1.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {

        ValidationResult validationResult = new ValidationResult("400", "Erro de validação.");
        validationResult.addFieldResult(exception.getFieldName(), exception.getMessage());

        return Response.status(Status.BAD_REQUEST).entity(validationResult).build();

    }
}