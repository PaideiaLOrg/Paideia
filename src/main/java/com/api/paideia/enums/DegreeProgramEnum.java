package com.api.paideia.enums;

public enum DegreeProgramEnum {

    ENSINO_MEDIO("ensino medio"), GRADUACAO("graduação"), ESPECIALIZACAO("especialização"),
    MESTRADO("mestrado"), DOUTORADO("doutorado"), POS_DOUTORADO("pos doutorado");

    private String degreeProgramEnum = "";

    private DegreeProgramEnum(String degreeProgramEnum) {
        this.degreeProgramEnum = degreeProgramEnum;
    }

    public String getDegreeProgramEnum() {
        return this.degreeProgramEnum;
    }
}
