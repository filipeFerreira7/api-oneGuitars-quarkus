package br.unitins.tp1.oneGuitars.validation;

public class ConstraintViolationError extends br.unitins.tp1.oneGuitars.util.Error {
    private String error;
    private String detail;

    public ConstraintViolationError(String code, String message){
        super(code, message);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    
}
