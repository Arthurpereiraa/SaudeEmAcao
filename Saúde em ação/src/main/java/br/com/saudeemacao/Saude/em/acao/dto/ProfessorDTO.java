package br.com.saudeemacao.Saude.em.acao.dto;

import lombok.Data;

@Data
public class ProfessorDTO {
    private Integer idProfessor;
    private String nome;
    private String telefone;
    private String especialidades;
    private String fotoPerfil;
}
