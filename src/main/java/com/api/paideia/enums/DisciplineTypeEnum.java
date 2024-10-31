package com.api.paideia.enums;

public enum DisciplineTypeEnum {
    OBRIGATORIA("Obrigatória"), OPTATIVA("Optativa"),
    ELETIVA("Eletiva");

    private String disciplineType = "";

    private DisciplineTypeEnum(String disciplineType) {
        this.disciplineType = disciplineType;
    }

    public String getDisciplineTypeEnum() {
        return this.disciplineType;
    }

}
