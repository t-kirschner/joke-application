package com.sqs.jokeme;

import jakarta.persistence.*;

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

    public Joke(String joke, String language) {
        this.joke = joke;
        this.language = language;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
