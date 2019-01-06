package app.goagile.web.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Card {
    @Id
    public String id;
    public String idParent;
    public String author;
    public String date;
    public String title;
    public String subtitle;
    public String template;
    public String type;
    public String mediaPlaceholder;
    public String links;
    public String tags;
    public String code;
}