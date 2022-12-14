package br.com.acrtech.planningpoker.usuarioms.dto;

import br.com.acrtech.planningpoker.usuarioms.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class UsuarioDto implements Serializable {
    private UUID id;
    @NotNull @Size(min = 2, max = 100, message = "O nome precisa ter entre 2 e 100 caracteres")
    private String nome;
    @NotNull @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull @Size(min = 6, max = 20, message = "A senha precisa ter entre 6 e 20 caracteres")
    private String senha;

    public UsuarioDto(Usuario usuario) {
        BeanUtils.copyProperties(usuario, this);
    }
}
