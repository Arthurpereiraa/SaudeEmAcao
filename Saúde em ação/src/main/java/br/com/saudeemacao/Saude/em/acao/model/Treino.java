package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTreino;

    @ManyToOne
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "idAluno", referencedColumnName = "idAluno")
    private Aluno aluno;

    private String descricao;
    private int duracao;
    private String videoTreino;
}
