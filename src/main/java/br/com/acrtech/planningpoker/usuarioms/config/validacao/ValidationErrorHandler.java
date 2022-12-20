package br.com.acrtech.planningpoker.usuarioms.config.validacao;

import br.com.acrtech.planningpoker.usuarioms.exception.ErroAoSalvarUsuarioException;
import br.com.acrtech.planningpoker.usuarioms.exception.ErroEmailDuplicadoException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;
    private final HttpServletRequest request;

    public ValidationErrorHandler(MessageSource messageSource, HttpServletRequest request) {
        this.messageSource = messageSource;
        this.request = request;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeFormularioDto handleErroValidacao(BindException exception){
        List<ErroValidacao> errosDeValidacoes = new ArrayList<>();
        var erros = exception.getBindingResult().getFieldErrors();
        erros.forEach(error -> {
            var mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            var erroValidacao = new ErroValidacao(error.getField(), mensagem);
            errosDeValidacoes.add(erroValidacao);
        });
        return new ErroDeFormularioDto(request.getContextPath(), HttpStatus.BAD_REQUEST.toString(), errosDeValidacoes);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErroEmailDuplicadoException.class)
    public ErroGeral handleEmailDuplicado(Throwable exception){

        return new ErroGeral(request.getContextPath(), HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ErroAoSalvarUsuarioException.class)
    public ErroGeral handleErro500(Throwable exception){
        return new ErroGeral(request.getContextPath(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }
}
