package br.com.saudeemacao.Saude.em.acao.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlunoCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter entre 10 e 11 dígitos numéricos")
    private String telefone;
}
