package io.kaplab.cards.api.controller;

import io.kaplab.cards.api.model.Content;
import io.kaplab.cards.api.repository.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/")
public class ContentRestController {

    @Autowired
    private ContentRepository repository;

    @CrossOrigin
    @RequestMapping("content")
    public List<Content> findAll() {
        final List<Content> cards = repository.findAll();
        return cards;
    }

    @CrossOrigin
    @RequestMapping(value = "content", method = RequestMethod.POST)
    public void save(@RequestBody Content content) {
        repository.save(content);
    }

}
