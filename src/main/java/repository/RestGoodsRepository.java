package repository;

import models.Category;
import models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestGoodsRepository extends JpaRepository<Goods, Integer> {
    Goods getGoodsByName(String name);
}
