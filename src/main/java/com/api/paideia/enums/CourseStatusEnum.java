package com.api.paideia.enums;

public enum CourseStatusEnum {

    CURSANDO("Cursando"), CONCLUIDO("Concluido"), TRANCADO("Trancado");

    private String course_status = "";

    private CourseStatusEnum(String course_status) {
        this.course_status = course_status;
    }

    public String getCourseStatusEnum() {
        return this.course_status;
    }
}
