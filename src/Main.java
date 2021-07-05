import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // I feel like we should have a non static Menu function that can return an error.
        // If it does, we should handle that error in main() and discard the return value.
        // And call the Menu function again.

        // I think this needs to be re-written.
        // While userchoice != 10
        // Go to menu


        int userChoice = Menu();
        while (userChoice != 10)
            userChoice = Menu();
    }

    public static int Menu() {

        Data data = new Data();
        int selection;
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("---------------------\n");
        System.out.println("1 - List All Foods");
        System.out.println("2 - Add Food");
        System.out.println("3 - Delete Item?");
        System.out.println("0 - Quit\n");

        if(!inputScanner.hasNextInt()) return 10;

        List<Food> foodList;

        selection = inputScanner.nextInt();
        switch (selection) {
            case 0: // Exits Program.
                System.exit(0);
            case 1:
                System.out.println("Food Menu.");
                // Call Food Menu Function
                foodList = data.getFoodList();
                System.out.println(("-----------------------------------------------------------------------------"));
                System.out.printf("%10s, %10s", "FOOD NAME", "IS KETO FRIENDLY");
                System.out.println();
                System.out.println(("-----------------------------------------------------------------------------"));
                for (int i = 0; i < foodList.size(); i++) {
                    System.out.println(String.format("%10s %10s",
                            foodList.get(i).getName(), foodList.get(i).isKetoFriendly()));
                }
                System.out.println("These are all foods.");
                return 1;
            case 2:
                // System.out.println("Add Food Function");
                Food newFood = data.createFood();
                if (newFood != null) {
                    foodList = data.getFoodList();
                    foodList.add(newFood);
                    data.storeFoodArrayList(foodList);
                }
                return 1;
            case 3:
                System.out.println("Delete Food Function");
                System.out.print("Enter the name of the food you want to delete: ");

                inputScanner = new Scanner(System.in);

                data.deleteFoodByName(inputScanner.nextLine());
                return 1;
            default:
                return 10;
        }
    }
}
