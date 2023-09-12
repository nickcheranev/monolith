package ru.ncheranev.rss.monolith.mapper;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.springframework.stereotype.Component;
import ru.ncheranev.rss.monolith.domain.Category;
import ru.ncheranev.rss.monolith.domain.Feed;
import ru.ncheranev.rss.monolith.domain.FeedItem;
import ru.ncheranev.rss.monolith.model.FeedDto;
import ru.ncheranev.rss.monolith.model.FeedItemDto;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FeedMapper {
    public FeedDto mapToFeed(SyndFeed syndFeed) {
        FeedDto feedDto = new FeedDto();
        feedDto.setDescription(syndFeed.getDescription().trim());
        feedDto.setLink(syndFeed.getLink().trim());
        feedDto.setTitle(syndFeed.getTitle().trim());
        feedDto.getItems().addAll(syndFeed.getEntries().stream().map(this::mapEntry).collect(Collectors.toList()));
        return feedDto;
    }

    private FeedItemDto mapEntry(SyndEntry syndEntry) {
        FeedItemDto item = new FeedItemDto();
        item.setDescription(syndEntry.getDescription().getValue().trim());
        item.setTitle(syndEntry.getTitle().trim());
        item.setLink(syndEntry.getLink().trim());
        item.setPubDate(syndEntry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        item.getCategories().addAll(syndEntry.getCategories().stream().map(category -> category.getName().trim()).collect(Collectors.toList()));
        return item;
    }

    public Feed mapToFeedEntity(FeedDto feedDto) {
        Feed feedEntity = new Feed();
        feedEntity.setDescription(feedDto.getDescription());
        feedEntity.setLink(feedDto.getLink());
        feedEntity.setTitle(feedDto.getTitle());
        List<Category> categoryEntities = new ArrayList<>();
        feedEntity.getItems()
                .addAll(feedDto.getItems().stream()
                        .map(item -> mapEntity(item, feedEntity, categoryEntities))
                        .collect(Collectors.toList()));

        for(var category : categoryEntities) {
            List<FeedItem> items = feedEntity.getItems().stream()
                    .filter(item -> item.getCategories().stream()
                            .anyMatch(itemCategory -> itemCategory.getName().equals(category.getName())))
                    .collect(Collectors.toList());
            category.getFeedItems().addAll(items);
        }

        return feedEntity;
    }

    private FeedItem mapEntity(FeedItemDto feedItemDto, Feed feedEntity, List<Category> categoryEntities) {
        FeedItem itemEntity = new FeedItem();
        itemEntity.setDescription(feedItemDto.getDescription());
        itemEntity.setTitle(feedItemDto.getTitle());
        itemEntity.setLink(feedItemDto.getLink());
        itemEntity.setPubDate(feedItemDto.getPubDate());

        for (String categoryName : feedItemDto.getCategories()) {
            Category category = mapCategory(categoryName, categoryEntities);
            itemEntity.getCategories().add(category);
        }

        itemEntity.setFeed(feedEntity);
        return itemEntity;
    }

    private Category mapCategory(String name, List<Category> categoryEntities) {
        Optional<Category> foundCategory = categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getName().equals(name)).findFirst();
        if (foundCategory.isEmpty()) {
            Category newCategory = new Category();
            newCategory.setName(name);
            categoryEntities.add(newCategory);
            return newCategory;
        } else {
            return foundCategory.get();
        }
    }
}
