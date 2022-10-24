package repository;

import models.Category;
import models.Goods;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
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

    public ArrayList<Category> GetCategories(){
        return categories;
    }

}
