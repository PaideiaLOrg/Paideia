package com.api.paideia.enums;

public enum KnowledgeAreaEnum {

    CIENCIAS_AGRARIAS("Ciencias Agrarias"), CIENCIAS_BIOLÓGICAS("Ciencias Biológicas"),
    CIENCIAS_DA_SAUDE("Ciencias da Saude"),
    CIENCIAS_EXATAS_E_DA_TERRA("Ciencias Exatas e da Terra"), CIENCIAS_HUMANAS("Ciencias Humanas"),
    CIENCIAS_SOCIAIS_APLICADAS("Ciencias Sociais Aplicadas"), ENGENHARIA("Engenharia"), LINGUISTICA("Linguistica"),
    LETRAS("Letras"), ARTES("Artes");

    private String knowledge_area = "";

    private KnowledgeAreaEnum(String knowledge_area) {
        this.knowledge_area = knowledge_area;
    }

    public String getKnowledgeAreaEnum() {
        return this.knowledge_area;
    }

}
