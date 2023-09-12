package ru.ncheranev.rss.monolith.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class FeedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String link;
    private String description;
    private LocalDate pubDate;
    @ManyToOne
    @JoinColumn(name="feed_id", nullable=false)
    private Feed feed;
    @ManyToMany(mappedBy = "feedItems")
    private List<Category> categories = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedItem other = (FeedItem) obj;
        return Objects.equals(id, other.getId());
    }
}
