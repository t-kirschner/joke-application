package com.sqs.jokeapplication;

public enum Language {
    ENGLISH(""), GERMAN("lang=de&");

    public final String url;

    Language(String url) {
        this.url = url;
    }
}