import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Data {

    String fileName = "FoodStorage.txt";

    public void storeFoodArrayList() {
        OutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Needs to be filled using getFoodArrayList() and then we append the new item to it.
            List<Food> foodList = new ArrayList<Food>();
            //foodList.add();

            for (Food food : foodList) {
                objectOutputStream.writeObject(food);
            }
        }
        catch (IOException ex) { // (IOException | ParseException ex)
            ex.printStackTrace();
        }
    }

    public void getFoodArrayList() {
        String fileName = "MyEmptyFile.txt";
        try {
            FileInputStream inputFile = new FileInputStream("FoodStorage.txt");
            ObjectInputStream objectInput = new ObjectInputStream(inputFile);

            while (true) {
                Food food = (Food) objectInput.readObject();
                System.out.println(food.getName());
                System.out.println(food.isKetoFriendly());
                System.out.println();
            }
        }
        catch (EOFException eof) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Needs to be re-written so it doesn't overwrite previous item.
    // Currently planning to get array of stored foods & appending
    // the new food to the array, then saving the array to file again.
    // Might also want to change file to .db instead. It's binary
    // anyway it seems...
    public Food addFood() {

        String foodName;
        boolean isKetoFriendly;

        System.out.print("Enter the name of the food: ");
        Scanner scannerInput = new Scanner(System.in);
        foodName = scannerInput.nextLine();

        System.out.println("\nIs it Keto friendly? Yes/No: ");
        if (scannerInput.hasNext("y") || scannerInput.hasNext("yes"))
            isKetoFriendly = true;
        else if (scannerInput.hasNext("n") || scannerInput.hasNext("no"))
            isKetoFriendly = false;
        else
            return null;
        /*
        scannerInput = new Scanner(System.in);
        String userInput = scannerInput.toString().toLowerCase();

        if (userInput == "y" || userInput == "yes")
            isKetoFriendly = true;
        else if (userInput == "n" || userInput == "no")
            isKetoFriendly = false;
        else {
            System.out.println("Confusing user Input. ");
            return null;
        }
         */
        return new Food(foodName, isKetoFriendly);
    }

    public void storeFood(Food food) {
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("FoodStorage.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(food);
            objOps.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ex){
                System.out.println(ex);
            }
        }

    }

    public void displayFoods(){

        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("FoodStorage.txt");
            objIs = new ObjectInputStream(fileIs);

            Food food = (Food) objIs.readObject();



            System.out.println(food.getId());
            System.out.println(food.getName());
            System.out.println(food.isKetoFriendly());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){

            }
        }
    }
}
