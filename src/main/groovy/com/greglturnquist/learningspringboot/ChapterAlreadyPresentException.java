package com.greglturnquist.learningspringboot;

public class ChapterAlreadyPresentException extends RuntimeException {

    public ChapterAlreadyPresentException(String name) {
        super("Chapter already present with name " +name);
    }

}
