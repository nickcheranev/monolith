package ru.ncheranev.rss.monolith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MonolithApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void restRun() {
        MonolithApplication.main(new String[]{});
    }
}
