package com.api.paideia.enums;

public enum ReferenceTypeEnum {
    LIVRO("Livro"), CAPITULO_DE_LIVRO("Capítulo de livro"),
    ARTIGO_DE_REVISTA("Artigo de revista"),
    WEBPAGE("Webpage"), OUTROS("Outros");

    private String referenceType = "";

    private ReferenceTypeEnum(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getReferenceTypeEnum() {
        return this.referenceType;
    }
}
