package com.greglturnquist.learningspringboot;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Chapter {

    @Id
    public String id;
    public String name;

    public Chapter(String name) {
        this.name = name;
    }
    
}
