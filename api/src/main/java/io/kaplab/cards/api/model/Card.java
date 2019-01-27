package io.kaplab.cards.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
public class Card {
    @Id
    public String id;
    public Map<String, Object> content;
}
