package repository;

import models.Category;
import models.Goods;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategoryRepository {
    private ArrayList<Category> categories = new ArrayList<Category>(){
        {
            add(new Category(new ArrayList<Category>(){
            {
                add(new Category(null, new ArrayList<Goods>(){
                    {
                        add(new Goods("a", 12));
                        add(new Goods("b", 12));
                    }
                }));
            }
        }
        , null));
            add(new Category(null, new ArrayList<Goods>(){
                {
                    add(new Goods("c", 14));
                    add(new Goods("d", 15));
                    add(new Goods("e", 16));
                }
            }));
        }
    };

    public ArrayList<Category> GetCategories(){
        return categories;
    }

    public void AddCategory(Category category){
        categories.add(category);
    }

    public void DeleteCategory(Category category){
        categories.remove(category);
    }

    public void ChangeCategory(Category oldCategory, Category newCategory){
        int position = categories.indexOf(oldCategory);
        categories.remove(oldCategory);
        categories.add(position, newCategory);
    }
}
