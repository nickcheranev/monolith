package ru.ncheranev.rss.monolith.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ncheranev.rss.monolith.mapper.FeedMapper;
import ru.ncheranev.rss.monolith.model.FeedDto;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private LoaderService loaderService;
//    @Autowired
//    private ObjectMapper objectMapper;
    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedMapper feedMapper;
    @Override
    public void run(String... args) throws Exception {
//        String url = "https://news.ru/rss/category/post/economics/";
//        FeedDto feedDto = loaderService.load(url);
////        log.info("feed: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(feedDto));
//
//        var saved = feedService.save(feedMapper.mapToFeedEntity(feedDto));
//        log.info("saved: {}", saved);
    }
}
