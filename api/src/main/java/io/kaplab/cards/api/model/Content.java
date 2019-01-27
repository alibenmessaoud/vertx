package io.kaplab.cards.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
public class Content {
    @Id
    public String id;
    public String type;
    public Map<String, Object> data;
}
