package br.com.acrtech.planningpoker.usuarioms.controller;

import br.com.acrtech.planningpoker.usuarioms.dto.UsuarioDto;
import br.com.acrtech.planningpoker.usuarioms.exception.UsuarioNaoEncontradoException;
import br.com.acrtech.planningpoker.usuarioms.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @Operation(
        summary = "Salva um usuário",
        description = "Este endpoint tem por objetivo possibilitar a criação de novos usuários dentro do sistema. " +
            "Os campos nome, e-mail e senha são obrigatórios. A senha não pode ter menos de 6 caracteres."
    )
    @PostMapping
    public ResponseEntity<UsuarioDto> save(@RequestBody @Valid UsuarioDto usuario){
        log.info("Salvando usuário {}", usuario.getNome());
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @Operation(
        summary = "Lista todos os usuários",
        description = "Este endpoint tem por objetivo listar todos os usuários cadastrados no sistema"
    )
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(
        summary = "Lista um usuário específico baseado em seu UUID",
        description = "Este endpoint tem por objetivo listar um usuário específico cadastrado no sistema baseado em seu UUID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@Parameter(description = "UUID do usuário a ser pesquisado") @PathVariable UUID id){
        try {
            return ResponseEntity.ok(usuarioService.findById(id));
        } catch (UsuarioNaoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
        summary = "Deleta um usuário específico baseado em seu UUID",
        description = "Este endpoint tem por objetivo deletar um usuário específico cadastrado no sistema baseado em seu UUID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@Parameter(description = "UUID do usuário a ser excluido") @PathVariable UUID id){
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
