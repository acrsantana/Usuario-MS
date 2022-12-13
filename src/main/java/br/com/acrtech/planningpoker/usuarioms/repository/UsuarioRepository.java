package br.com.acrtech.planningpoker.usuarioms.repository;

import br.com.acrtech.planningpoker.usuarioms.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
