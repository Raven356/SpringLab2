package models;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;


    @Column(name = "Name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryId")
    private Category category;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Category> categories;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Goods> goods;

    public Category(){

    }

    public Category(ArrayList<Category> categories, ArrayList<Goods> goods, String name, Category category){
        this.categories = categories;
        this.goods = goods;
        this.name = name;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category(String name, ArrayList<Category> categories, ArrayList<Goods> goods){
        this.name = name;
        this.categories = categories;
        this.goods = goods;
    }

    public int getId() {
        return Id;
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
    }

    public void setGoods(List<Goods> goods){
        this.goods = goods;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<Category> getCategories(){
        return categories;
    }

    public List<Goods> getGoods(){
        return goods;
    }

    public String getName(){
        return name;
    }

}
