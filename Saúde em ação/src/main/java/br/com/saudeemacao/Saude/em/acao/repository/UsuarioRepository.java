package br.com.saudeemacao.Saude.em.acao.repository;

import br.com.saudeemacao.Saude.em.acao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
