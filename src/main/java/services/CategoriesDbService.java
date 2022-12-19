package services;

import models.Category;
import models.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import repository.RestCategoryRepository;
import repository.RestGoodsRepository;

@Service
@ComponentScan(basePackages={"repository"})

public class CategoriesDbService {

    @Autowired
    private RestCategoryRepository restCategoryRepository;

    @Autowired
    private RestGoodsRepository restGoodsRepository;

    public int AddCategory(Category category){

        return restCategoryRepository.save(category).getId();
    }

    public Category SelectCategoryByName(String name){
        return restCategoryRepository.getCategoryByName(name);
    }

    public int AddGoods(Goods goods){
        return restGoodsRepository.save(goods).getId();
    }

    public Goods SelectGoodsByName(String name){
        return restGoodsRepository.getGoodsByName(name);
    }

    public void DeleteGoods(Goods goods){
        restGoodsRepository.delete(goods);
    }

    public void DeleteCategory(Category category){
        restCategoryRepository.delete(category);
    }

}
