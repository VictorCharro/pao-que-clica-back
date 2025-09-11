package br.com.paoqueclica.pao_que_clica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pao")
public class Pao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(name = "saldo")
    int saldo;

    @Column(name = "multiplicador")
    int multiplicador;

    @Column(name = "pao_por_segundo")
    private Integer paoPorSegundo;

    private int custoPadeiro;

    private LocalDateTime ultimaAtualizacao;
}
