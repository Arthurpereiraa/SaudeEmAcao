package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String senha;

    private String nome;

    // Outros campos que você achar necessários
}
