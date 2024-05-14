package br.com.clauvane.desafio.portifolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class MembroId implements Serializable {

    @OneToOne
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;

    @OneToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    public MembroId(Long idProjeto, Long idPessoa) {
        this.projeto = new Projeto(idProjeto);
        this.pessoa = new Pessoa(idPessoa);
    }

}
