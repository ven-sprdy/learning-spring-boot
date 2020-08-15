package com.greglturnquist.learningspringboot;

import com.greglturnquist.learningspringboot.entity.Chapter;
import org.springframework.context.ApplicationEvent;

public class ChapterCreatedEvent extends ApplicationEvent {

    public ChapterCreatedEvent(Chapter chapter) {
        super(chapter);
    }

}
