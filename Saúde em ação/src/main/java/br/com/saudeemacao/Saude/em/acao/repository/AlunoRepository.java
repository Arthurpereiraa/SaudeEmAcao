package br.com.saudeemacao.Saude.em.acao.repository;

import br.com.saudeemacao.Saude.em.acao.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
