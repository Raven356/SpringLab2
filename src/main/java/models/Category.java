package models;

import java.util.ArrayList;

public class Category {
    private ArrayList<Category> categories;
    private ArrayList<Goods> goods;

    public Category(ArrayList<Category> categories, ArrayList<Goods> goods){
        this.categories = categories;
        this.goods = goods;
    }

    public ArrayList<Category> GetCategories(){
        return categories;
    }

    public ArrayList<Goods> GetGoods(){
        return goods;
    }
}
