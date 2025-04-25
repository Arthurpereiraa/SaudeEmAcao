package br.com.saudeemacao.Saude.em.acao.controller;

import br.com.saudeemacao.Saude.em.acao.model.Comunidade;
import br.com.saudeemacao.Saude.em.acao.model.Mensagem;
import br.com.saudeemacao.Saude.em.acao.repository.ComunidadeRepository;
import br.com.saudeemacao.Saude.em.acao.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    MensagemRepository repository;

    @Autowired
    ComunidadeRepository comunidadeRepository;

    @GetMapping("/mensagens")
    public List<Mensagem> getMessagesByChat(@RequestParam String chatId) {
        return repository.findByChatId(chatId);
    }

    // Endpoint para buscar todas as comunidades
    @GetMapping("/comunidades")
    public List<Comunidade> getAllComunidades() {
        return comunidadeRepository.findAll(); // Use a instância injetada
    }

    // Endpoint para criar uma nova comunidade
    @PostMapping("/comunidades")
    public Comunidade createComunidade(@RequestBody Comunidade comunidade) {
        Comunidade save = comunidadeRepository.save(comunidade); // Use a instância injetada
        return save;
    }
}
