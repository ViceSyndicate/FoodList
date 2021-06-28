import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // I feel like we should have a non static Menu function that can return an error.
        // If it does, we should handle that error in main() and discard the return value.
        // And call the Menu function again.

        int userChoice = Menu();
        while (userChoice == 10) {
            System.out.println("Unexpected Input.");
            userChoice = Menu();
        }
        System.out.println("Exiting Program.");
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

        selection = inputScanner.nextInt();
        switch (selection) {
            case 0: // Exits Program.
                return 0;
            case 1:
                System.out.println("Food Menu.");
                // Call Food Menu Function
                data.getFoodArrayList();
                return 1;
            case 2:
                // System.out.println("Add Food Function");
                Food newFood = data.createFood();
                if (newFood != null) data.storeFood(newFood);
                return 2;
            case 3:
                System.out.println("Delete Food Function");
                // Call Delete Food Function
                return 3;
            default:
                return 10;
        }
    }
}
