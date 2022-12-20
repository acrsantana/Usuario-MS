package br.com.acrtech.planningpoker.usuarioms.service;

import br.com.acrtech.planningpoker.usuarioms.dto.UsuarioDto;
import br.com.acrtech.planningpoker.usuarioms.exception.ErroAoSalvarUsuarioException;
import br.com.acrtech.planningpoker.usuarioms.exception.ErroEmailDuplicadoException;
import br.com.acrtech.planningpoker.usuarioms.exception.UsuarioNaoEncontradoException;
import br.com.acrtech.planningpoker.usuarioms.model.Usuario;
import br.com.acrtech.planningpoker.usuarioms.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.validation.Valid;
import java.net.ConnectException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service @Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> findAll(){
        log.info("Listando todos os usuarios");
        return usuarioRepository.findAll().stream().map(UsuarioDto::new).toList();
    }

    public UsuarioDto findById(UUID id) {
        log.info("Recuperando o usuario com id {}", id);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            log.error("Usuario com o id {} não encontrado.", id);
            throw new UsuarioNaoEncontradoException("O usuario com o id " + id + " não foi localizado");
        }
        return new UsuarioDto(optionalUsuario.get());
    }

    public void deleteById(UUID id){
        log.info("Deletando o usuário com id {}", id);
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto save(@Valid UsuarioDto usuario){
        log.info("Iniciando salvamento do usuário {}", usuario.getNome());
        try {
            return new UsuarioDto(usuarioRepository.save(new Usuario(usuario)));
        } catch (CannotCreateTransactionException e) {
            log.error("Erro ao conectar ao banco de dados");
            throw new ErroAoSalvarUsuarioException("Problemas ao conectar com o banco de dados, tente novamente mais tarde");
        } catch (DataIntegrityViolationException e) {
            log.error("O e-mail {} já existe na base de dados", usuario.getEmail());
            throw new ErroEmailDuplicadoException("E-mail informado já existe na base de dados. Altere a informação e tente novamente.");
        } catch (Exception e) {
            log.error("Erro desconhecido ao salvar usuário. Verifique pilha abaixo");
            log.info(e.getMessage(), e);
            throw new ErroAoSalvarUsuarioException("Problemas ao salvar o usuário, tente novamente mais tarde");
        }
    }
}
