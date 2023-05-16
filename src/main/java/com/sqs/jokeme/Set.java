package com.sqs.jokeme;

public enum Set {
    ANY("Any"), PROGRAMMING("Programming"), CHRISTMAS("Christmas"), ENGLISH(""), GERMAN("lang=de&");

    public final String option;

    Set(String option) {
        this.option = option;
    }
}