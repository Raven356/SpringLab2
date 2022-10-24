package services;
import models.Category;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import repository.UserRepository;

import java.util.ArrayList;

@Service
@ComponentScan(basePackages={"repository"})
public class CategoriesService
{

    private CategoryRepository categoryRepository;

    public CategoriesService(CategoryRepository CatRepo)
    {
        categoryRepository = CatRepo;
    }
    public ArrayList<Category> GetCategories(){
        return categoryRepository.GetCategories();
    }

    public void SetCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public void AddCategory(Category category){
        categoryRepository.GetCategories().add(category);
    }

    public void DeleteCategory(Category deleteCategory){
        Category searched;
        ArrayList<Category> categories = categoryRepository.GetCategories();
        for(Category category : categories){
            if(category.GetCategories()!= null){
                searched = SearchForCategory(category, deleteCategory);
            }
            else {
                searched = null;
            }
            if(searched != null){
                categories.remove(searched);
                return;
            }
            else if(category.GetName().equals(deleteCategory.GetName())){
                categories.remove(category);
                return;
            }
        }

    }

    public void ChangeCategory(Category oldCategory, Category newCategory){
        Category searched;
        ArrayList<Category> categories = categoryRepository.GetCategories();
        for(Category category : categories){
            if(category.GetCategories() != null)
                searched = SearchForCategory(category, oldCategory);
            else
                searched = null;
            if(searched != null){
                Change(searched, newCategory);
                return;
            }
            else if(category.GetName().equals(oldCategory.GetName()))
            {
                Change(category, newCategory);
                return;
            }
        }

    }

    private Category SearchForCategory(Category catForSearch, Category searchCategory){
        Category searched = null;
        for(Category category:catForSearch.GetCategories()){
            if(category.GetCategories() != null)
                searched = SearchForCategory(category, searchCategory);
            else if(category.GetName().equals(searchCategory.GetName()))
                return category;
        }
        return searched;
    }

    private void Change(Category oldCategory, Category newCategory){
        ArrayList<Category> categories = categoryRepository.GetCategories();
        int position = categories.indexOf(oldCategory);
        categories.remove(oldCategory);
        categories.add(position, newCategory);
    }
}
