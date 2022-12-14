package repository;

import models.Category;
import models.Goods;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CategoryRepository {
    private ArrayList<Category> categories = new ArrayList<Category>(){
        {
            add(new Category("a", new ArrayList<Category>(){
            {
                add(new Category("a.b",null, new ArrayList<Goods>(){
                    {
                        add(new Goods("a", 12));
                        add(new Goods("b", 12));
                    }
                }));
            }
        }
        , null));
            add(new Category("c", null, new ArrayList<Goods>(){
                {
                    add(new Goods("c", 14));
                    add(new Goods("d", 15));
                    add(new Goods("e", 16));
                }
            }));
        }
    };

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public void setCategories(ArrayList<Category> categories)
    {
        this.categories = categories;

    }
}
