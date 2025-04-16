package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idAluno", referencedColumnName = "idAluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    private Professor professor;

    private Date dataHora;
    private String mensagem;
    private String videoUrl;
    private String foto;
}
