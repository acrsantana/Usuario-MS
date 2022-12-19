package br.com.acrtech.planningpoker.usuarioms.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ErroValidacao {
    private String campo;
    private String mensagem;
}
