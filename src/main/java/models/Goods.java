package models;

public class Goods {
    public String name;
    public int price;

    public Goods(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String GetName(){
        return name;
    }

    public int GetPrice(){
        return price;
    }

    public String GetFullInfo(){
        return "Name = " + name + ", Price = " + price;
    }
}
