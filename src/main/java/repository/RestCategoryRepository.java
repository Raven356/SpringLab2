package repository;

import models.Category;
import models.Goods;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestCategoryRepository extends JpaRepository<Category, Integer>, CrudRepository<Category, Integer> {
    Category getCategoryByName(String name);
    @Query("SELECT g FROM Goods g JOIN Category c ON c.Id = g.category.Id WHERE c.name = :name")
    List<Goods> GetGoodByCategoryNameQuery(@Param("name") String name);
}
