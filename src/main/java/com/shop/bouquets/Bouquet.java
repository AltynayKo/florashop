package com.shop.bouquets;

import com.shop.accessories.Accessor;
import com.shop.flowers.BaseFlower;
import java.util.List;

public class Bouquet implements Comparable<Bouquet>{
    private String name;
    private List<BaseFlower> flowers;
    private List<Accessor> accessors;
    private int flowersAmount;
    private int bouquetPrice;
    private int bouquetLength;

    Bouquet(String name, int flowersAmount, List<BaseFlower> flowers, List<Accessor> accessors, int bouquetPrice, int bouquetLength) {
        this.name = name;
        this.flowersAmount = flowersAmount;
        this.flowers = flowers;
        this.accessors = accessors;
        this.bouquetLength = bouquetLength;
        this.bouquetPrice = bouquetPrice;
    }

    public int getBouquetPrice() {
        return bouquetPrice;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Bouquet [" +
                "name='" + name + '\'' +
                ", amount of flowers=" + flowersAmount +
                ", bouquet price=" + bouquetPrice +
                ", bouquet length=" + bouquetLength +
                ", flowers=" + flowers +
                ", accessors=" + accessors +
                ']';
    }

    @Override
    public int compareTo(Bouquet o) {
        return this.bouquetPrice - o.bouquetPrice;
    }
}
