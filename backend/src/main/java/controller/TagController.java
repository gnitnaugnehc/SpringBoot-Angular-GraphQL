package controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import model.Tag;
import repository.TagRepository;

@Controller
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @QueryMapping
    Iterable<Tag> tags() {
        return tagRepository.findAll();
    }

    @QueryMapping
    Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(null);
    }
}
