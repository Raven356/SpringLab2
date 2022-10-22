package models;

public class Goods {
    private String name;
    private int price;

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
}
