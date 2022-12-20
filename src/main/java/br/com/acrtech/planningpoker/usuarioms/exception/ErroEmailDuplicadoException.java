package br.com.acrtech.planningpoker.usuarioms.exception;

public class ErroEmailDuplicadoException extends RuntimeException {
    public ErroEmailDuplicadoException(String message) {
        super(message);
    }

    public ErroEmailDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
