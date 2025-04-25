package br.com.saudeemacao.Saude.em.acao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Anotação JPA para mapeamento de tabela
@Table(name = "mensagens") // Define o nome da tabela no MySQL
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Tipo alterado para Long

    private String usuario;
    private String conteudo;

    @Column(name = "chat_id") // Opcional: define o nome da coluna
    private String chatId;
}
