package ru.ncheranev.rss.monolith.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class FeedDto {
    private String title;
    private String description;
    private String link;
    private List<FeedItemDto> items = new ArrayList<>();
}
