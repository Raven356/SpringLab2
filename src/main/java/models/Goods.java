package models;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Entity
@Table(name = "Goods")
@NamedQuery(name = "Goods.selectGoodsByPrice", query = "SELECT g from Goods g where g.price = :price")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "CategoryId")
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public Goods(){

    }

    public Goods(String name, int price, Category category, int id){
        this.name = name;
        this.price = price;
        this.category = category;
        this.Id = id;
    }

    public Goods(String name, int price, Category category){
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public Goods(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }

    public String getFullInfo(){
        return "Name = " + name + ", Price = " + price;
    }
}
