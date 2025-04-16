package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfessor;

    private String nome;
    private String telefone;
    private String especialidades;
    private String fotoPerfil;
}
