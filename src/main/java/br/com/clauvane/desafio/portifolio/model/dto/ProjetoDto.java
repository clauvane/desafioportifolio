package br.com.clauvane.desafio.portifolio.model.dto;

import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.model.enums.RiscoProjeto;
import br.com.clauvane.desafio.portifolio.model.enums.StatusProjeto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProjetoDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 200, message = "O tamanho máximo permitido é de 200 caracteres.")
    private String nome;

    @NotNull(message = "O gerente é obrigatório.")
    private Pessoa gerente;

    private Date dataInicio;

    private Date dataPrevisaoFim;

    private Date dataFim;

    private Double orcamento;

    @Size(max = 5000, message = "O tamanho máximo permitido é de 5000 caracteres.")
    private String descricao;

    private StatusProjeto status;

    private RiscoProjeto risco;

}
