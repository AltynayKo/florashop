/*
The enum list stores predefined accessories with their characteristics.
So user can choose and add to bouquet an accessor by specifying only their name. Price criteria will be added by default.

The list can be replaced with external file in file.
 */

package com.shop.accessories;

import com.shop.flowers.BaseFlower;
import com.shop.flowers.Lily;
import com.shop.flowers.Peony;
import com.shop.flowers.Rose;

public enum AccessoriesList {
    Wrapper(new Accessor("Wrapper", 500)),
    Postcard(new Accessor("Postcard", 100)),
    Ribbon(new Accessor("Ribbon", 300));

    final Accessor accessor;

    AccessoriesList(Accessor accessor) {
        this.accessor = accessor;
    }

    public Accessor getAccessor(){
        return accessor;
    }

}
