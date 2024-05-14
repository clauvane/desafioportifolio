package br.com.clauvane.desafio.portifolio.model.enums;

public enum StatusProjeto {

    EM_ANALISE(1, "Em Análise"),
    ANALISE_REALIZADA(2, "Análise Realizada"),
    ANALISE_APROVADA(3, "Análise Aprovada"),
    INICIADO(4, "Iniciado"),
    PLANEJADO(5, "Planejado"),
    EM_ANDAMENTO(6, "Em Andamento"),
    ENCERRADO(7, "Encerrado"),
    CANCELADO(8, "Cancelado");

    private Integer id;
    private String descricao;

    private StatusProjeto(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

}
