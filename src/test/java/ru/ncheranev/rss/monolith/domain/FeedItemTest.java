package ru.ncheranev.rss.monolith.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FeedItemTest {

    @Test
    void testHashCode() {
        assertThat(new FeedItem().hashCode()).isEqualTo(new FeedItem().hashCode());
        assertThat(new FeedItem().setId(1L).hashCode()).isEqualTo(new FeedItem().setId(1L).hashCode());
        assertThat(new FeedItem().setId(1L).hashCode()).isNotEqualTo(new FeedItem().hashCode());
    }

    @Test
    void testEquals() {
        assertThat(new FeedItem()).isEqualTo(new FeedItem());
        assertThat(new FeedItem().setId(1L)).isEqualTo(new FeedItem().setId(1L));
        assertThat(new FeedItem().setId(1L)).isNotEqualTo(new FeedItem());

        var o1 = new FeedItem();
        var o2 = o1;
        assertThat(o1).isEqualTo(o2);
        assertThat(o1).isNotEqualTo(null);
        assertThat(o1).isNotEqualTo("text");
    }
}