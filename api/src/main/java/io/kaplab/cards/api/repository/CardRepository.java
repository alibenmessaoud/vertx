package io.kaplab.cards.api.repository;

import io.kaplab.cards.api.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
