package br.com.clauvane.desafio.portifolio.model.entity;

import br.com.clauvane.desafio.portifolio.model.enums.RiscoProjeto;
import br.com.clauvane.desafio.portifolio.model.enums.StatusProjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio", columnDefinition = "DATE")
    private Date dataInicio;

    @Column(name = "data_previsao_fim", columnDefinition = "DATE")
    private Date dataPrevisaoFim;

    @Column(name = "data_fim", columnDefinition = "DATE")
    private Date dataFim;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusProjeto status;

    @Column(name = "orcamento")
    private Double orcamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco")
    private RiscoProjeto risco;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    public Projeto(Long id) {
        this.id = id;
    }

}
