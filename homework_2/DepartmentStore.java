import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The <code>DepartmentStore</code> class implements an <code>ItemList</code> object with a user interface.
 */
public class DepartmentStore {

    public static Scanner input;

    /**
     * Every Single method is a private helper method
     */

    //Prints Starting menu
    private static void printMenu() {
        System.out.println();
        System.out.println("I - Insert Item");
        System.out.println("M - Move Item");
        System.out.println("L - List by Location");
        System.out.println("P - Print Items");
        System.out.println("O - Checkout");
        System.out.println("C - Clean Store");
        System.out.println("U - Update Inventory System");
        System.out.println("Q - Quit");
    }

    // Gets option from user
    private static String getAnswerFromUser() {
        input = new Scanner(System.in);
        String[] options = {"I", "M", "L", "P", "O", "C", "U", "Q"}; // Available options
        System.out.print("\nEnter Option: ");
        String answer = input.next().toUpperCase();
        for (String i : options) {
            if (i.equals(answer))
                return answer;
        }
        System.out.println("Enter A Valid Letter!!");
        return getAnswerFromUser();
    }

    // Next few methods help get the Name, Price, RFID#,
    // Original Location, Current Location, and a New Location

    private static String getNameFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter Name: ");
        return input.nextLine();
    }

    private static double getPriceFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter Price: ");
        return input.nextDouble();
    }

    private static String getRfidNumFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter RFID#: ");
        return input.next();
    }

    private static String getOriginalLocationFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter Original Location: ");
        return input.next();
    }

    private static String getCurrentLocationFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter Current Location: ");
        return input.next();
    }

    private static String getNewLocationFromUser() {
        input = new Scanner(System.in);
        System.out.print("Enter New Location: ");
        return input.next();
    }

    public static void main(String[] args) {
        ItemList itemList = new ItemList();
        while (true) {
            try {
                printMenu();
                String answer = getAnswerFromUser();

                // Depending on the value the user passes to answer, the program will either quit or continue to ask what user
                // wants to do to the list of items.
                System.out.println();
                if (answer.equals("Q"))
                    break;
                switch (answer) {
                    case "I" -> itemList.insertInfo(getNameFromUser(), getRfidNumFromUser(), getPriceFromUser(), getOriginalLocationFromUser());
                    case "M" -> itemList.moveItem(getRfidNumFromUser(), getCurrentLocationFromUser(), getNewLocationFromUser());
                    case "L" -> itemList.printByLocation(getCurrentLocationFromUser());
                    case "P" -> itemList.printAll();
                    case "O" -> {
                        double total = itemList.checkOut(getCurrentLocationFromUser());
                        System.out.printf("\nThis Cart's Total is: $%.2f\n\n", total);
                    }
                    case "C" -> itemList.cleanStore();
                    case "U" -> itemList.removeAllPurchased();
                }
            } catch (LocationException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Must be a number");
            }
        }
        System.out.println("\nGoodbye!");
    }
}
