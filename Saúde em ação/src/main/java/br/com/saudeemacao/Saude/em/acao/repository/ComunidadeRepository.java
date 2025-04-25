package br.com.saudeemacao.Saude.em.acao.repository;

import br.com.saudeemacao.Saude.em.acao.model.Comunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadeRepository extends JpaRepository<Comunidade, Integer> {
}
