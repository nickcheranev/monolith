package ru.ncheranev.rss.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncheranev.rss.monolith.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
