package br.com.saudeemacao.Saude.em.acao.controller;

import br.com.saudeemacao.Saude.em.acao.dto.ProfessorDTO;
import br.com.saudeemacao.Saude.em.acao.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
@Validated
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarTodos() {
        List<ProfessorDTO> professores = professorService.listarTodos();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable Integer id) {
        ProfessorDTO professorDTO = professorService.buscarPorId(id);
        return new ResponseEntity<>(professorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> salvar(@RequestBody @Validated ProfessorDTO professorDTO) {
        ProfessorDTO professorSalvo = professorService.salvar(professorDTO);
        return new ResponseEntity<>(professorSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(@PathVariable Integer id, @RequestBody @Validated ProfessorDTO professorDTO) {
        ProfessorDTO professorAtualizado = professorService.atualizar(id, professorDTO);
        return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        professorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
