package services;
import exceptions.CollisionException;
import exceptions.NotFoundException;
import models.Category;
//import org.springframework.beans.factory.annotation.Autowired;
import models.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan(basePackages={"repository"})
public class CategoriesService
{
    @Autowired
    private CategoryRepository categoryRepository;

    //public CategoriesService(CategoryRepository CatRepo)
    //{
        //categoryRepository = CatRepo;
    //}
    public ArrayList<Category> getCategories(){
        return categoryRepository.getCategories();
    }

    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public void AddCategory(Category category) throws CollisionException {
        for(Category cat : categoryRepository.getCategories()){
            if(cat.getName().equals(category.getName()))
                throw new CollisionException();
        }
        categoryRepository.getCategories().add(category);
    }

    public void DeleteCategory(Category deleteCategory) throws Exception {
        Category searched;
        ArrayList<Category> categories = categoryRepository.getCategories();
        for(Category category : categories){
            if(category.getCategories()!= null){
                searched = SearchForCategory(category, deleteCategory);
            }
            else {
                searched = null;
            }
            if(searched != null){
                categories.remove(searched);
                return;
            }
            else if(category.getName().equals(deleteCategory.getName())){
                categories.remove(category);
                return;
            }
        }
        throw new NotFoundException();

    }

    public void DeleteGoods(Category category){
        Category searched;
        ArrayList<Category> categories = categoryRepository.getCategories();
        for(Category category1 : categories){
            if(category1.getGoods() != null) {
                if (category1.getCategories() != null) {
                    searched = SearchForCategory(category1, category);
                } else
                    searched = null;
                if (searched == null) {
                    for (Goods goods : category1.getGoods()) {
                        if (goods.getName().equals(category.getGoods().get(0).getName())) {
                            category1.getGoods().remove(goods);
                        }
                    }
                } else {
                    for (Goods goods : searched.getGoods()) {
                        if (goods.getName().equals(category.getGoods().get(0).getName())) {
                            searched.getGoods().remove(goods);
                        }
                    }
                }
            }
        }
    }



    public void ChangeCategory(Category oldCategory, Category newCategory) throws Exception {
        Category searched;
        ArrayList<Category> categories = categoryRepository.getCategories();
        for(Category category : categories){
            if(category.getCategories() != null)
                searched = SearchForCategory(category, oldCategory);
            else
                searched = null;
            if(searched != null){
                Change(searched, newCategory);
                return;
            }
            else if(category.getName().equals(oldCategory.getName()))
            {
                Change(category, newCategory);
                return;
            }
        }

        throw new NotFoundException();
    }

    private Category SearchForCategory(Category catForSearch, Category searchCategory){
        Category searched = null;
        for(Category category:catForSearch.getCategories()){
            if(category.getCategories() != null)
                searched = SearchForCategory(category, searchCategory);
            else if(category.getName().equals(searchCategory.getName()))
                return category;
        }
        return searched;
    }

    private void Change(Category oldCategory, Category newCategory){
        ArrayList<Category> categories = categoryRepository.getCategories();
        int position = categories.indexOf(oldCategory);
        if(newCategory.getCategories() == null && newCategory.getGoods() == null){
            newCategory.setCategories(oldCategory.getCategories());
            newCategory.setGoods(oldCategory.getGoods());
        }
        categories.remove(oldCategory);
        categories.add(position, newCategory);
    }
}
