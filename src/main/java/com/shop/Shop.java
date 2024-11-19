/*
main class to interact with
 */
package com.shop;

import com.shop.accessories.Accessor;
import com.shop.accessories.AccessoriesList;
import com.shop.bouquets.NewBouquet;
import com.shop.customExceptions.DeletionProblemException;
import com.shop.customExceptions.EmptyValueException;
import com.shop.customExceptions.NegativeNumberException;
import com.shop.flowers.BaseFlower;
import com.shop.flowers.FlowersList;

import java.util.*;

public class Shop {
    static List<BaseFlower> mainFlower;
    static NewBouquet mainBouquet;
    static List<Accessor> mainAccessor;
    static boolean flFound, accFound;
    static String bouquetName;

    /*
    Main menu is always displayed, until option #7 is entered.
    @throws NegativeNumberException, EmptyValueException, DeletionProblemException exceptions
     */
    public static void main(String[] args) throws NegativeNumberException, EmptyValueException, DeletionProblemException {
        try {
            mainBouquet = new NewBouquet();
            while (true) {

                displayMenu();

                Scanner sc = new Scanner(System.in);
                int operationNumber = sc.nextInt();

                displaySelectedOption(operationNumber);

                if (operationNumber == 7) break;
            }
        } catch (InputMismatchException e) {
            System.out.println("The value should be alphabetic type for name or numeric type for option");
        }
    }

    //displays the options of main menu
    public static void displayMenu() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Please input correct operation number to proceed:");
        System.out.println("1 - Create a bouquet");
        System.out.println("2 - Get available/created bouquet");
        System.out.println("3 - Sort bouquets by price value");
        System.out.println("4 - Delete a bouquet");
        System.out.println("5 - Get available flowers list");
        System.out.println("6 - Get available accessories list");
        System.out.println("7 - Quit");
        System.out.println("-----------------------------------------------------------------");

    }

    //based on the entered value, perform desired actions
    public static void displaySelectedOption(int operationNumber) throws NegativeNumberException, EmptyValueException, DeletionProblemException {
        if (operationNumber<0){
            throw new NegativeNumberException();
        }
        switch (operationNumber) {
                case 1 -> {
                        bouquetSubOption();
                }
                case 2 -> {
                    if (mainBouquet.isBouquetListEmpty()){
                        System.out.println("These bouquets are available:");
                        getAvailableBouquets();
                    } else {
                        System.out.println("There is no bouquet in the shop");
                    }
                }
                case 3 -> {
                    if (mainBouquet.isBouquetListEmpty()) {
                        System.out.println("Available bouquets are sorted by price");
                        System.out.println(mainBouquet.getSortedBouquets());
                    } else {
                        System.out.println("There is no bouqu`et to sort");
                    }
                }
                case 4 -> {
                    if (mainBouquet.isBouquetListEmpty()) {
                        deleteBouquetOption();
                    } else {
                        System.out.println("There is no bouquet to delete");
                    }
                }
                case 5 -> {
                    System.out.println("These flowers are available, so you can create a bouquet with them");
                    System.out.println(getAvailableFlowers());
                }
                case 6 -> {
                    System.out.println("These accessories are available, so you can create a bouquet with them");
                    System.out.println(getAvailableAccessories());
                }
                case 7 -> {
                    System.out.println("App is closing...");
                }
                default -> displayMenu();
            }
    }

    /*
    Used in 5th option
    @return available flowers list which can be added to a bouquet
     */
    public static String getAvailableFlowers() {
        return Arrays.toString(FlowersList.values());
    }
    /*
    Used in 6th option
     @return available flowers list which can be added to a bouquet
     */
    public static String getAvailableAccessories() {
        return Arrays.toString(AccessoriesList.values());
    }

    /*
    * Used to generate list of flowers, which should be added to the bouquet
    * @return ArrayList of added flowers
    */
    public static boolean collectFlowers(String flowerName) {
        flFound = false;
        for (FlowersList list : FlowersList.values()) {
            if (flowerName.equals(list.name())) {
                mainFlower.add(FlowersList.valueOf(list.name()).getBaseFlower());
                flFound = true;
            }
        }
        return flFound;
    }

    /*
     * Used to generate list of accessories, which should be added to the bouquet
     * @return ArrayList of added accesories
     */
    public static boolean collectAccessors(String accessorName) {
        accFound = false;
        for (AccessoriesList list : AccessoriesList.values()) {
            if (accessorName.equals(list.name())) {
                mainAccessor.add(AccessoriesList.valueOf(list.name()).getAccessor());
                accFound = true;
                break;
            }
        }
        return accFound;
    }

    /*
     * Displays sub-option while creating a bouquet
     */
    public static void bouquetSubOption() throws NegativeNumberException, EmptyValueException, DeletionProblemException {
        System.out.println("To create a bouquet, you need to enter bouquet name, flowers amount, names and accessories");
        System.out.println("Please enter 1 - to proceed with creation, 2 - to go back main menu");
        Scanner sc = new Scanner(System.in);
        int subOperationNumber = sc.nextInt();
        if (subOperationNumber<0){
            throw new NegativeNumberException();
        }
        if (subOperationNumber == 1) {
            createBouquetOption();
        }
    }

    /*
     * The main method to create a bouquet. Shows relevant messages if a problem arises
     * @throws EmptyValueException, DeletionProblemException
     */
    public static void createBouquetOption() throws EmptyValueException, DeletionProblemException {
        mainFlower = new ArrayList<>();
        mainAccessor = new ArrayList<>();
        System.out.println("Please enter bouquet name:");
        Scanner sc = new Scanner(System.in);
        bouquetName = sc.nextLine();
        if (bouquetName.trim().isEmpty()){
            throw new EmptyValueException("Bouquet name can't be empty");
        }
        System.out.println("Please enter flowers amount in the bouquet");
        int flowersAmount = sc.nextInt();

        if (flowersAmount <= 0) {
            System.out.println("Flowers amount can't be <= 0");
            displaySelectedOption(1);
        }

        System.out.println("Please enter flower name one by one (case sensitive)");
        System.out.println("Available flowers: "+ getAvailableFlowers());
        for (int i = 0; i < flowersAmount; i++) {
            Scanner scFlowerName = new Scanner(System.in);
            String flowerName = scFlowerName.nextLine();
            if (flowerName.trim().isEmpty()){
                throw new EmptyValueException("Flower name can't be empty");
            }
            if (collectFlowers(flowerName)) {
                System.out.println("Success: The flower is added");
            } else {
                System.out.println("Fail: The flower is not found, pls try again");
                i--;
            }
        }

        System.out.println("Please enter accessories amount in the bouquet:");
        Scanner scAccessor = new Scanner(System.in);
        int accessoriesAmount = scAccessor.nextInt();

        if (accessoriesAmount < 0) {
            System.out.println("Accessories amount can't be < 0");
        } else if (accessoriesAmount>0) {
            System.out.println("Please enter accessor name one by one (Available accessories "+ getAvailableAccessories()+"):");
        }
        for (int i = 0; i < accessoriesAmount; i++) {
            Scanner scAccessorName = new Scanner(System.in);
            String accessorName = scAccessorName.nextLine();
            if (accessorName.trim().isEmpty()){
                throw new EmptyValueException("Accessor name can't be empty");
            }
            if (collectAccessors(accessorName)){
                System.out.println("Success: The accessor is added");
            } else {
                System.out.println("Fail: The accessor is not found, pls try again");
                i--;
            }
        }

        mainBouquet.createBouquet(bouquetName, flowersAmount, mainFlower, mainAccessor);
        System.out.println("Success: Bouquet " + bouquetName + " is created successfully");
    }

    //used to get available/created bouquets list
    public static void getAvailableBouquets(){
        if (mainBouquet.isBouquetListEmpty()){
            System.out.println(mainBouquet.getBouquets());
        } else {
            System.out.println("There is no bouquet");
        }
    }

    //used to delete unnecessary bouquet
    public static void deleteBouquetOption() throws DeletionProblemException {
        try {
            System.out.println("Please enter bouquet name to delete it from the list");
            Scanner sc = new Scanner(System.in);
            String bouquetName = sc.nextLine();
            if (mainBouquet.deleteBouquet(bouquetName)) {
                System.out.println("Success: " + bouquetName + " bouquet has been deleted");
            } else {
                System.out.println("Fail: No bouquet with name " + bouquetName);
            }
        } catch (InputMismatchException e){
            System.err.println("Not allowed symbol is entered");
        }
    }
}
