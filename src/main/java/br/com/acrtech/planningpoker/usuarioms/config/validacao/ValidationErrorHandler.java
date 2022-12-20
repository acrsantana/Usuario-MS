package br.com.acrtech.planningpoker.usuarioms.config.validacao;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;

    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeFormularioDto handle(BindException exception){
        List<ErroValidacao> errosDeValidacoes = new ArrayList<>();
        var erros = exception.getBindingResult().getFieldErrors();
        erros.forEach(error -> {
            var mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            var erroValidacao = new ErroValidacao(error.getField(), mensagem);
            errosDeValidacoes.add(erroValidacao);
        });
        return new ErroDeFormularioDto(errosDeValidacoes);
    }
}
