package br.com.clauvane.desafio.portifolio.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PessoaDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O tamanho máximo permitido é de 100 caracteres.")
    private String nome;

    @Size(max = 14, message = "O tamanho máximo permitido é de 14 caracteres.")
    private String cpf;

    private Date dataNascimento;

    private Boolean funcionario;

    private Boolean gerente;

}
