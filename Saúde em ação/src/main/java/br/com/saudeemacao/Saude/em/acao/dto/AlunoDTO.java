package br.com.saudeemacao.Saude.em.acao.dto;

import lombok.Data;

@Data
public class AlunoDTO {
        private Integer idAluno;
        private String nome;
        private String email;
        private String cpf;
        private String telefone;
}
