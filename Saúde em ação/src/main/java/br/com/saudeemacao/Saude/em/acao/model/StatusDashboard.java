package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
public class StatusDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto")
    private Produto produto;

    private Date dataRegistro;
    private BigDecimal totalVendido;
    private int produtosAtivos;
    private int produtosReservados;
    private int aguardoPagamento;
}
