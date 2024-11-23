package br.unitins.tp1.faixas.validation;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.faixas.util.Error;

public class ValidationError extends Error {

    private record FieldError(String fieldName, String message) {
    };

    private List<FieldError> errors = null;

    public ValidationError(String code, String message) {
        super(code, message);
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void addFieldError(String fieldName, String message) {
        if (errors == null)
            errors = new ArrayList<FieldError>();
        errors.add(new FieldError(fieldName, message));
    }

}
