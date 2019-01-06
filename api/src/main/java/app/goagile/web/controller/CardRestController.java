package app.goagile.web.controller;

import app.goagile.web.model.Card;
import app.goagile.web.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CardRestController {

    @Autowired
    private CardRepository repository;

    @RequestMapping("card/")
    public List<Card> findAll() {
        final List<Card> cards = repository.findAll();
        log.info("Fetching cards from database {}", cards);
        return cards;
    }

    @RequestMapping(value = "card/", method = RequestMethod.POST)
    public void save(@RequestBody Card card) {
        log.info("Storing card in database {}", card);
        repository.save(card);
    }

}
