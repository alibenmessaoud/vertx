package io.kaplab.cards.api.repository;

import io.kaplab.cards.api.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content, String> {
}
