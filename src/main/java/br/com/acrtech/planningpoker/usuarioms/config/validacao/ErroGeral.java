package br.com.acrtech.planningpoker.usuarioms.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ErroGeral {
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String path;
    private String status;
    private String mensagem;
    public ErroGeral(String path, String status, String mensagem) {
        this.path = path;
        this.status = status;
        this.mensagem = mensagem;
    }
}
