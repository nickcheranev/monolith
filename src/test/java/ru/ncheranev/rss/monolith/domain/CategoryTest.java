package ru.ncheranev.rss.monolith.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    void testHashCode() {
        assertThat(new Category().hashCode()).isEqualTo(new Category().hashCode());
        assertThat(new Category().setId(1L).hashCode()).isEqualTo(new Category().setId(1L).hashCode());
        assertThat(new Category().setId(1L).hashCode()).isNotEqualTo(new Category().hashCode());
    }

    @Test
    void testEquals() {
        assertThat(new Category()).isEqualTo(new Category());
        assertThat(new Category().setId(1L)).isEqualTo(new Category().setId(1L));
        assertThat(new Category().setId(1L)).isNotEqualTo(new Category());

        var o1 = new Category();
        var o2 = o1;
        assertThat(o1).isEqualTo(o2);
        assertThat(o1).isNotEqualTo(null);
        assertThat(o1).isNotEqualTo("text");
    }
}