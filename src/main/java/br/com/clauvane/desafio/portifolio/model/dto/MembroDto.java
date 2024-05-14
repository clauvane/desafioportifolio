package br.com.clauvane.desafio.portifolio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class MembroDto {

    @NotNull(message = "O idPessoa é obrigatório.")
    private Long idPessoa;

    @NotNull(message = "O idProjeto é obrigatório.")
    private Long idProjeto;

}
