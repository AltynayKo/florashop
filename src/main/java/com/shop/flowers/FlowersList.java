/*
The enum list stores predefined flowers with their criteria.
So user can choose and add to bouquet a flower by specifying only their name. Price and length criteria will be added by default.

The list can be replaced with external file in file.
 */

package com.shop.flowers;

public enum FlowersList {
    Rose(new Rose(100, 1500)),
    Lily(new Lily(50, 2000)),
    Peony(new Peony(80, 3000)),
    Tulip(new Tulip(50, 1000)),
    Orchid(new Orchid(70, 5000));

    final BaseFlower baseFlower;

    FlowersList(BaseFlower flower) {
        this.baseFlower = flower;
    }

    public BaseFlower getBaseFlower(){
        return baseFlower;
    }

}
