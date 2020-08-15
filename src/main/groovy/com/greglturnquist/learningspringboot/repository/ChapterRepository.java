package com.greglturnquist.learningspringboot.repository;

import com.greglturnquist.learningspringboot.entity.Chapter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
    Mono<Chapter> findByName(String name);
}
