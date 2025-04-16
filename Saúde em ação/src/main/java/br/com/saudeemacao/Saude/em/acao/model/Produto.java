package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @ManyToOne
    @JoinColumn(name = "idAluno", referencedColumnName = "idAluno")
    private Aluno aluno;

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private String cor;
    private String tamanho;
    private int estoque;
    private String imgUrl;
}
