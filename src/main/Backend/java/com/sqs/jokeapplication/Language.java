package com.sqs.jokeapplication;

// saves selectable languages with specific url part used to build joke api request
public enum Language {
    ENGLISH(""), GERMAN("lang=de&");

    public final String url;

    Language(String url) {
        this.url = url;
    }
}