package br.com.saudeemacao.Saude.em.acao.controller;

import br.com.saudeemacao.Saude.em.acao.dto.AlunoCreateDTO;
import br.com.saudeemacao.Saude.em.acao.dto.AlunoDTO;
import br.com.saudeemacao.Saude.em.acao.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Validated
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarTodos() {
        List<AlunoDTO> alunos = alunoService.listarTodos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Integer id) {
        AlunoDTO alunoDTO = alunoService.buscarPorId(id);
        return new ResponseEntity<>(alunoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@RequestBody @Validated AlunoCreateDTO alunoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AlunoDTO alunoSalvo = alunoService.salvar(alunoDTO);
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable Integer id, @RequestBody @Validated AlunoCreateDTO alunoDTO) {
        AlunoDTO alunoAtualizado = alunoService.atualizar(id, alunoDTO);
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        alunoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
