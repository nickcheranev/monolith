package ru.ncheranev.rss.monolith.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncheranev.rss.monolith.domain.Category;
import ru.ncheranev.rss.monolith.domain.Feed;
import ru.ncheranev.rss.monolith.repository.CategoryRepository;
import ru.ncheranev.rss.monolith.repository.FeedItemRepository;
import ru.ncheranev.rss.monolith.repository.FeedRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.ncheranev.rss.monolith.util.CollectionUtil.distinctByKey;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final FeedItemRepository feedItemRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Feed save(Feed feed) {
        List<Category> savedCategories = categoryRepository.findAll();
        List<Category> newCategories = feed.getItems().stream()
                .flatMap(feedItem -> feedItem.getCategories().stream())
                .filter(distinctByKey(Category::getName))
                .collect(Collectors.toList());
        var saved = feedRepository.save(feed);
        feedItemRepository.saveAll(feed.getItems());
        categoryRepository.saveAll(newCategories);
        return saved;
    }
}
