// This is the repo'ed final version
// Importing the 'java.util' package to use the Scanner and ArrayList classes
import java.util.*;
// The Scanner class allows us to read user input
// ArrayList class allows us to create arrays that can store and manipulate Candle objects

//Crete Candle class with desired properties
class Candle {
    private String name;
    private String description;
    private int inventory;
    private int burnTime;
    private double dollarPerBurnTime;
    private double price;

    // Constructor for the Candle class
    public Candle(String name, String description, int inventory, int burnTime, double price) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.burnTime = burnTime;
        this.price = price;
        this.dollarPerBurnTime = price / burnTime;
    }

    // Getter methods for the Candle class
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInventory() {
        return inventory;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public double getDollarPerBurnTime() {
        return dollarPerBurnTime;
    }

    public double getPrice() {
        return price;
    }

    // Static method to get a list of Candle objects
    public static List<Candle> getCandles() {
        List<Candle> candles = new ArrayList<>();
        // Adding the shop's candles to the array list
        candles.add(new Candle("Enchanted Rose", "A captivating blend of roses and magic", 10, 8, 13.95));
        candles.add(new Candle("Belle's Library", "A cozy scent of old books and adventure", 7, 6, 10.50));
        candles.add(new Candle("Beast's Castle", "A mysterious aroma of woods and enchantment", 5, 9, 12.75));
        candles.add(new Candle("Cogsworth", "A delightful fragrance of warm spices and wisdom", 4, 5, 9.25));
        candles.add(new Candle("Lumière", "A joyful blend of vanilla and flickering light", 6, 7, 11.50));
        return candles;
    }
}

// Create the candle shop class that will take order and print the receipt
public class CandleShop {
    public static void main(String[] args) {
        List<Candle> candles = new ArrayList<>();
        // Adding all Candle objects from the getCandles() method to the 'candles' list
        candles.addAll(Candle.getCandles());

        // Create scanner to allow us to receive user input
        Scanner scanner = new Scanner(System.in);

        //Create console greeting
        System.out.println("Welcome to The Beauty and the Beast Candle Shop!");

        //Create Variables for the selected candles for purchase and the total amount
        double totalAmount = 0;
        List<Candle> purchasedCandles = new ArrayList<>();

        // Loop to allow the user to select and buy candles until they choose to exit
        do {
            System.out.println("\nCandles Available for Purchase:");
            // Loop though candles to list them for the user
            for (int i = 0; i < candles.size(); i++) {
                Candle candle = candles.get(i);
                // Displaying the available candles with their name and description
                System.out.println((i + 1) + ". " + candle.getName() + " - " + candle.getDescription() + " $" + candle.getPrice());
            }
            //Prompt user to select a candle to buy
            System.out.print("Select a candle to buy (1-" + candles.size() + ") or enter 0 to exit: ");
            // read integer input from user and store it in the selection variable
            int selection = scanner.nextInt();
            // "Consumes" and removes that extra newline character from the "input stream" so no bugs are created for the next input
            scanner.nextLine();
            // If user enters 0, cancel the sale
            if (selection == 0) {
                break;
                // If the user doesn't enter a valid candle number, tell the user and continue
            } else if (selection < 1 || selection > candles.size()) {
                System.out.println("Invalid selection.");
                continue;
            }

            Candle selectedCandle = candles.get(selection - 1);

            // Prompt user to order quantity for selected candle
            System.out.print("Enter quantity for " + selectedCandle.getName() + ": ");

            // Read user input and clear new line
            int quantity = scanner.nextInt();
            scanner.nextLine();

            // If user enters 0 or less for quantity return "invalid"
            // If user enters amount greater than the inventory also return "invalid"
            if (quantity <= 0 || quantity > selectedCandle.getInventory()) {
                System.out.println("Invalid quantity.");
                continue;
            }

            // Creating a new Candle object with the selected properties and adding it to the 'purchasedCandles' list
            purchasedCandles.add(new Candle(selectedCandle.getName(), selectedCandle.getDescription(), quantity, selectedCandle.getBurnTime(), selectedCandle.getPrice()));

            // Create total variable
            double subtotal = selectedCandle.getPrice() * quantity;
            totalAmount += subtotal;

            // Ask user if they want to continue purchasing candles, if yes, continue loop, if not, print receipt
            System.out.print("Do you want to buy more candles? (yes/no): ");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));

        // Start receipt print
        System.out.println("\nReceipt");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // (Loop over all candles (Candle candle) in (:) the purchasedCandles list)
        // Loop to print ordered candle details
        for (Candle candle : purchasedCandles) {
            System.out.println(candle.getName());
            System.out.println("Description: " + candle.getDescription());
            System.out.println("Amount: " + candle.getInventory());
            System.out.println("Burn Time: " + candle.getBurnTime() + " hours");
            // Use " String.format("%.2f" " to format the prices to always be a number with two decimals so prices of $12.5 or $12 show up as $12.50 or $12.00
            System.out.println("Dollar per Burn Time: $" + String.format("%.2f", candle.getDollarPerBurnTime()));
            System.out.println("Price: $" + String.format("%.2f", candle.getPrice() * candle.getInventory()));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        // Print total and thank you
        System.out.println("\nTotal Price: $" + String.format("%.2f", totalAmount));
        System.out.println("Thank you for visiting the Candle Shop!");
    }
}