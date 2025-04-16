package br.com.saudeemacao.Saude.em.acao.service;

import br.com.saudeemacao.Saude.em.acao.dto.AlunoCreateDTO;
import br.com.saudeemacao.Saude.em.acao.dto.AlunoDTO;
import br.com.saudeemacao.Saude.em.acao.exception.RecursoNaoEncontradoException;
import br.com.saudeemacao.Saude.em.acao.model.Aluno;
import br.com.saudeemacao.Saude.em.acao.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public List<AlunoDTO> listarTodos() {
        return alunoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AlunoDTO buscarPorId(Integer id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno com ID " + id + " não encontrado"));
        return toDTO(aluno);
    }

    @Transactional
    public AlunoDTO salvar(AlunoCreateDTO dto) {
        if (alunoRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (alunoRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha()); // Criptografar senha depois com Spring Security
        aluno.setCpf(dto.getCpf());
        aluno.setTelefone(dto.getTelefone());

        Aluno salvo = alunoRepository.save(aluno);
        return toDTO(salvo);
    }

    public AlunoDTO atualizar(Integer id, AlunoCreateDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno com ID " + id + " não encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
        aluno.setCpf(dto.getCpf());
        aluno.setTelefone(dto.getTelefone());

        return toDTO(alunoRepository.save(aluno));
    }

    public void excluir(Integer id) {
        if (!alunoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Aluno com ID " + id + " não encontrado");
        }
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setIdAluno(aluno.getIdAluno());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setCpf(aluno.getCpf());
        dto.setTelefone(aluno.getTelefone());
        return dto;
    }
}
