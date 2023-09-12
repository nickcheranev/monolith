package ru.ncheranev.rss.monolith.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FeedTest {

    @Test
    void testHashCode() {
        assertThat(new Feed().hashCode()).isEqualTo(new Feed().hashCode());
        assertThat(new Feed().setId(1L).hashCode()).isEqualTo(new Feed().setId(1L).hashCode());
        assertThat(new Feed().setId(1L).hashCode()).isNotEqualTo(new Feed().hashCode());
    }

    @Test
    void testEquals() {
        assertThat(new Feed()).isEqualTo(new Feed());
        assertThat(new Feed().setId(1L)).isEqualTo(new Feed().setId(1L));
        assertThat(new Feed().setId(1L)).isNotEqualTo(new Feed());

        var o1 = new Feed();
        var o2 = o1;
        assertThat(o1).isEqualTo(o2);
        assertThat(o1).isNotEqualTo(null);
        assertThat(o1).isNotEqualTo("text");
    }
}