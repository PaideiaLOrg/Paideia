package com.api.paideia.enums;

public enum DisciplineStatus {
    NAO_CURSADA("Não Cursada"), CURSANDO("Cursando"),
    APROVADO("Aprovado"), REPROVADO("Reprovado");

    private String disciplineStatus = "";

    private DisciplineStatus(String disciplineStatus) {
        this.disciplineStatus = disciplineStatus;
    }

    public String getDisciplineStatus() {
        return this.disciplineStatus;
    }
}
