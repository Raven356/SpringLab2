package repository;

import models.Category;
import models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestGoodsRepository extends JpaRepository<Goods, Integer>, CrudRepository<Goods, Integer> {
    Goods getGoodsByName(String name);
    List<Goods> selectGoodsByPrice(@Param("price") Integer price);
}
