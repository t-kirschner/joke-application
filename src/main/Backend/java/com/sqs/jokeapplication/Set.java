package com.sqs.jokeapplication;

public enum Set {
    ANY("Any"), PROGRAMMING("Programming"), CHRISTMAS("Christmas"), ENGLISH(""), GERMAN("lang=de&");

    public final String url;

    Set(String option) {
        this.url = option;
    }
}