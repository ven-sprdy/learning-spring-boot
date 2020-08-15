package com.greglturnquist.learningspringboot.controller;

import com.greglturnquist.learningspringboot.entity.Chapter;
import com.greglturnquist.learningspringboot.service.ChapterService;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.URI;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }


    @GetMapping
    public Flux<Chapter> getAll() {
        return this.chapterService.all();
    }

    @GetMapping("/{id}")
    Publisher<Chapter> getById(@PathVariable("id") String id) {
        return this.chapterService.getById(id);
    }

    @PostMapping
    Publisher<ResponseEntity<Chapter>> create(@RequestBody Chapter chapter) {
        return this.chapterService
                .create(chapter.getName())
                .map(c -> ResponseEntity.created(URI.create("/chapters/" + c.getId())).build()
                );

    }

    @DeleteMapping("/{id}")
    Publisher<Chapter> deleteById(@PathVariable String id) {
        return this.chapterService.deleteById(id);
    }

}
