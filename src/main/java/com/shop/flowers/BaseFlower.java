/*
Base flower is parent class for all other flowers
 */

package com.shop.flowers;

public class BaseFlower {
    private String name;
    private int length;
    private int price;
    public BaseFlower(String name, int length, int price) {
        this.name = name;
        this.length = length;
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return STR."\{name}, length = \{length}, price = \{price}";
    }
}
