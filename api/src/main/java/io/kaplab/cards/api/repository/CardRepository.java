package io.kaplab.cards.api.repository;

import io.kaplab.cards.api.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    public Card findByAuthor(String author);

    public List<Card> findByType(String type);

}
