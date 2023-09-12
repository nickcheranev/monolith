package ru.ncheranev.rss.monolith.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncheranev.rss.monolith.mapper.FeedMapper;
import ru.ncheranev.rss.monolith.model.FeedDto;

import java.io.IOException;
import java.net.URL;

@Service
public class LoaderService {
    @Autowired
    private FeedMapper feedMapper;
    public FeedDto load(String url) throws IOException, FeedException {
//        String url = "https://stackoverflow.com/feeds/tag?tagnames=rome";
        SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));

        return feedMapper.mapToFeed(syndFeed);
    }
}
