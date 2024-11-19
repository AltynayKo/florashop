package com.shop.bouquets;

import com.shop.accessories.Accessor;
import com.shop.customExceptions.DeletionProblemException;
import com.shop.flowers.BaseFlower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NewBouquet implements Comparator<Bouquet> {
    ArrayList<Bouquet> bouquets;
    StringBuilder builder;
    int bouquetPrice;
    int bouquetLength;

    public NewBouquet(){
        bouquets = new ArrayList<>();
    }

    private List<Bouquet> getAllBouquets(){
        return (List<Bouquet>) bouquets.clone();
    }

    public int calculateBouquetLength(List<BaseFlower> flowers){
        bouquetLength = flowers.getFirst().getLength();

        for (BaseFlower flower: flowers){
            if(flower.getLength()<bouquetLength){
                bouquetLength = flower.getLength();
            }
        }
        return bouquetLength;
    }

    public int calculateBouquetPrice(List<BaseFlower> flowers,  List<Accessor> accessors){
        bouquetPrice = 0;

        for (BaseFlower flower: flowers){
            bouquetPrice+= flower.getPrice();
        }
        for (Accessor accessor: accessors){
            bouquetPrice+= accessor.getPrice();
        }
        return bouquetPrice;
    }
    public void createBouquet(String bouquetName, int amount, List<BaseFlower> flowers, List<Accessor> accessors){
        bouquets.add(new Bouquet(bouquetName, amount, flowers, accessors, calculateBouquetPrice(flowers, accessors), calculateBouquetLength(flowers)));
    }

    public String getBouquets() {
        int i=1;
        builder = new StringBuilder();
        for (Bouquet bqts: bouquets){
            builder.append(i++);
            builder.append(". ");
            builder.append(bqts.toString());
            builder.append('\n');
        }
        return builder.toString();
    }
    public String getSortedBouquets(){
        List <Bouquet> list = getAllBouquets();
        Collections.sort(list);
        builder = new StringBuilder();
        for (Bouquet bqts: list){
            builder.append(bqts.toString());
            builder.append('\n');
        }
        return builder.toString();
    }

    public boolean deleteBouquet(String name) throws DeletionProblemException {
        try{
            for (int i = 0; i < bouquets.size(); i++) {
                if (name.equalsIgnoreCase(bouquets.get(i).getName())){
                    bouquets.remove(i);
                    return true;
                }
            }
        } catch (NullPointerException e){
            throw new NullPointerException("Trying to delete null value");
        }  catch (Exception e) {
            throw new DeletionProblemException("Problem occurred while deleting a Bouquet");
        }
        return false;
    }

    public boolean isBouquetListEmpty(){
        return bouquets != null && !bouquets.isEmpty();
    }


    @Override
    public int compare(Bouquet o1, Bouquet o2) {
        return o1.getBouquetPrice() - o2.getBouquetPrice();
    }
}
