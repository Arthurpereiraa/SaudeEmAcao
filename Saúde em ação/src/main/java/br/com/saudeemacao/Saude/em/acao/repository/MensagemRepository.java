package br.com.saudeemacao.Saude.em.acao.repository;

import br.com.saudeemacao.Saude.em.acao.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findByChatId(String chatId);
}