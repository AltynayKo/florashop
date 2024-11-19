
package com.shop.accessories;

public class Accessor {
    public String name;
    public int price;
    public Accessor(String name, int price){
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Accessor{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
