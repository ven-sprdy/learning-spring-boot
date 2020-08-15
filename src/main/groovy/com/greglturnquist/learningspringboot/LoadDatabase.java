package com.greglturnquist.learningspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(ChapterRepository chapterRepository) {
        return args -> {
            chapterRepository
                    .deleteAll()
                    .thenMany(
                         Flux.just("Quick Start with Java", "Reactive Web with Spring Boot", "...and more!")
                            .map(Chapter::new)
                            .flatMap(chapterRepository::save)
                    )
                    .thenMany(chapterRepository.findAll())
                    .subscribe(System.out::println );
        };
    }

}
