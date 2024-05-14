package br.com.clauvane.desafio.portifolio.model.enums;

public enum RiscoProjeto {

    BAIXO(1, "Baixo Risco"),
    MEDIO(2, "Médio Risco"),
    ALTO(3, "Alto Risco");

    private Integer id;
    private String descricao;

    private RiscoProjeto(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
