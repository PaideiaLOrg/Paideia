package com.api.paideia.enums;

public enum ResearchTypeEnum {
    AC("Artigo Científico"), IC("Iniciação Científica"), TCC("Trabalho de Conclusão de Curso"),
    MESTRADO("Mestrado"), DOUTORADO("Doutorado"), POS_DOUTORADO("Pós-Doutorado");

    private String researchType = "";

    private ResearchTypeEnum(String researchType) {
        this.researchType = researchType;
    }

    public String getResearchTypeEnum() {
        return this.researchType;
    }
}
