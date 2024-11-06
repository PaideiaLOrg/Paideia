package com.api.paideia.enums;

public enum CourseStatusEnum {

    CURSANDO("Cursando"), CONCLUIDO("Concluido"), TRANCADO("Trancado");

    private String courseStatus = "";

    private CourseStatusEnum(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseStatusEnum() {
        return this.courseStatus;
    }
}
