package br.com.acrtech.planningpoker.usuarioms.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ErroDeFormularioDto {

    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String path;
    private String status;
    private List<ErroValidacao> erros;

    public ErroDeFormularioDto(String path, String status, List<ErroValidacao> errosDeValidacoes) {
        this.path = path;
        this.status = status;
        this.erros = errosDeValidacoes;
    }
}
