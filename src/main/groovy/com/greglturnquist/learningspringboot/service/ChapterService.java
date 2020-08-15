package com.greglturnquist.learningspringboot.service;

import com.greglturnquist.learningspringboot.ChapterAlreadyPresentException;
import com.greglturnquist.learningspringboot.ChapterCreatedEvent;
import com.greglturnquist.learningspringboot.entity.Chapter;
import com.greglturnquist.learningspringboot.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ChapterService(ChapterRepository chapterRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.chapterRepository = chapterRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Flux<Chapter> all() {
        return this.chapterRepository.findAll();
    }

    public Mono<Chapter> getById(String id) {
        return this.chapterRepository.findById(id);
    }

    public Mono<Chapter> create(String name) {
        return this.chapterRepository
                .findByName(name)
                .flatMap(existingChapter -> Mono.error(new ChapterAlreadyPresentException((name))))
                .then(this.chapterRepository.save(new Chapter(name)))
                .doOnSuccess(chapter -> this.applicationEventPublisher.publishEvent(new ChapterCreatedEvent(chapter)));
    }

    public Mono<Chapter> deleteById(String id) {
        return this.chapterRepository
                .findById(id)
                .flatMap(chapter -> this.chapterRepository.deleteById(chapter.id).thenReturn(chapter));
    }

}
