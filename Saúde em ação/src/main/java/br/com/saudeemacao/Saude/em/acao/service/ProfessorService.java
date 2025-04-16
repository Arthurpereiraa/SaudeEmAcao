package br.com.saudeemacao.Saude.em.acao.service;

import br.com.saudeemacao.Saude.em.acao.dto.ProfessorDTO;
import br.com.saudeemacao.Saude.em.acao.model.Professor;
import br.com.saudeemacao.Saude.em.acao.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public List<ProfessorDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessorDTO buscarPorId(Integer id) {
        Professor p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return toDTO(p);
    }

    @Transactional
    public ProfessorDTO salvar(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setTelefone(dto.getTelefone());
        professor.setEspecialidades(dto.getEspecialidades());
        professor.setFotoPerfil(dto.getFotoPerfil());

        return toDTO(repository.save(professor));
    }

    @Transactional
    public ProfessorDTO atualizar(Integer id, ProfessorDTO dto) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professor.setNome(dto.getNome());
        professor.setTelefone(dto.getTelefone());
        professor.setEspecialidades(dto.getEspecialidades());
        professor.setFotoPerfil(dto.getFotoPerfil());

        return toDTO(repository.save(professor));
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setIdProfessor(professor.getIdProfessor());
        dto.setNome(professor.getNome());
        dto.setTelefone(professor.getTelefone());
        dto.setEspecialidades(professor.getEspecialidades());
        dto.setFotoPerfil(professor.getFotoPerfil());
        return dto;
    }
}

