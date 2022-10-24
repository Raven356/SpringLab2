package models;

import java.util.ArrayList;

public class Category {
    private ArrayList<Category> categories;
    private ArrayList<Goods> goods;
    private String name;

    public Category(String name, ArrayList<Category> categories, ArrayList<Goods> goods){
        this.name = name;
        this.categories = categories;
        this.goods = goods;
    }


    public void setCategories(ArrayList<Category> categories){
        this.categories = categories;
    }

    public void setGoods(ArrayList<Goods> goods){
        this.goods = goods;
    }
    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public ArrayList<Goods> getGoods(){
        return goods;
    }

    public String getName(){
        return name;
    }

}
