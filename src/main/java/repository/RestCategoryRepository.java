package repository;

import models.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestCategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByName(String name);

}
