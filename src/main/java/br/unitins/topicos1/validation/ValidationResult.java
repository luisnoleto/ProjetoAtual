package br.unitins.topicos1.validation;


import br.unitins.topicos1.application.Result;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult extends Result {

    record FieldResult(String fieldName, String message) {};
    private List<FieldResult> errors = null;
    
    public ValidationResult(String code, String message) {
        super(code, message);
    }

    public void addFieldResult(String fieldName, String message) {
        if (errors == null)
            errors = new ArrayList<FieldResult>();
        errors.add(new FieldResult(fieldName, message));
    }

    public List<FieldResult> getResults() {
        return errors.stream().toList();
    }
}