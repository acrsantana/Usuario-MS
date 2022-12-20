package br.com.acrtech.planningpoker.usuarioms.exception;

public class ErroAoSalvarUsuarioException extends RuntimeException {
    public ErroAoSalvarUsuarioException(String message) {
        super(message);
    }

    public ErroAoSalvarUsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
}
