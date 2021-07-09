import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Data {

    String fileName = "FoodStorage.txt";

    public void storeFoodArrayList(List<Food> foodList) {
        OutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Food food : foodList) {
                objectOutputStream.writeObject(food);
            }
        }
        catch (IOException ex) { // (IOException | ParseException ex)
            ex.printStackTrace();
        }
    }

    public List<Food> getKetoList(List<Food> foodList) {
        for (int i = 0; i < foodList.size();)
        {
            if (foodList.get(i).isKetoFriendly() == false) foodList.remove(i);
            i++;
        }
        return  foodList;
    }

    public List<Food> getFoodList() {
        List<Food> foodList = new ArrayList<Food>();
        try {
            FileInputStream inputFile = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(inputFile);

            while (true) {
                Food food = (Food) objectInput.readObject();
                foodList.add(food);
            }
        }
        catch (EOFException eof) {
            //System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return foodList;
    }

    public Food createFood() {

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

        return new Food(foodName, isKetoFriendly);
    }

    public void deleteFoodByName(String foodName) {
        List<Food> foodList = getFoodList();

        for(int i = 0; i < foodList.size(); i++){
            if (foodName.toLowerCase().equals(foodList.get(i).getName().toLowerCase())) {
                System.out.println("Removing: " + foodList.get(i).getName());
                foodList.remove(i);
            }
        }
        storeFoodArrayList(foodList);
    }
    // Below is depreciated.

    public void storeFood(Food food) {
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream(fileName);
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
            fileIs = new FileInputStream(fileName);
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
