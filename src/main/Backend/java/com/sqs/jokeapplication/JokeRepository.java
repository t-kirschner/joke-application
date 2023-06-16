package com.sqs.jokeapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// database repository interface
@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    long countByLanguage(String language);

    Joke findFirstByLanguage(String language);
}
