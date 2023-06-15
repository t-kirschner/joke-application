package com.sqs.jokeapplication;

import jakarta.persistence.*;

// saves all necessary data for a joke entity which is saved in the database
@Entity
@Table(name = "jokes")
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "joke", length = 500)
    private String joke;

    @Column(name = "language")
    private String language;


    public Joke() {
    }

    public Joke(String joke, Language language) {
        this.joke = joke;
        this.language = language.toString();
    }

    public String getJoke() {
        return joke;
    }
}