package ru.ncheranev.rss.monolith.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class FeedItemDto {
    private String title;
    private String link;
    private String description;
    private LocalDate pubDate;
    private List<String> categories = new ArrayList<>();
}
