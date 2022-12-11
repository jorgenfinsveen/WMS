package no.ntnu.jorgfi;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/** 
 * Represents a text-based User Interface.
 * 
 * <p>The user will only have direct interaction
 * with the user interface. The UI will create
 * an instance of the Warehouse class, and it 
 * will perform actions on this instance rather
 * than the user to directly access it.
 * 
 * <p>The communication will go through the console
 * only, where the user will write inputs to STDIN
 * and receive outputs to STDOUT.
 * 
 * <p>All methods belonging to this class is static,
 * making it possible to call methods without creating
 * an instance of this class.
 * 
 * @since       2022-11-03
 * @version     2022-12-09
 * @author      Candidate 10034
*/
public class UserInterface { 
    
    /** The Item-register which the user is managing. */
    private static final Warehouse warehouse = new Warehouse();
    /** Scanner object used to read user-inputs from STDIN. */
    private static final Scanner scanner = new Scanner(System.in);

    /** Universal option for exiting the application. */
    private static final String OPTION_EXIT = "0";


    /*
     * STARTUP MENU OPTIONS
     * ---------------------------------------------------------------
    */

    /** Startup Menu option for adding a new Item. */
    private static final String INIT_OPTION_ADD_ITEM = "1";
    /** Startup Menu option for populating the warehouse with predefined Items. */
    private static final String INIT_OPTION_FILL_INVENTORY = "2";


    /*
     * ITEM MENU OPTIONS
     * ---------------------------------------------------------------
    */

    /** Item Menu option for increasing the amount of the current Item. */
    private static final String ITEM_OPTION_INCREASE = "1";
    /** Item Menu option for decreasing the amount of the current Item. */
    private static final String ITEM_OPTION_DECREASE = "2";
    /** Item Menu option for changing the price of the current Item. */
    private static final String ITEM_OPTION_CHANGE_PRICE = "3";
    /** Item Menu option for changing the item price by adding a discount. */
    private static final String ITEM_OPTION_ADD_DISCOUNT = "4";
    /** Item Menu option for removing an Item instance and all its products from the inventory. */
    private static final String ITEM_OPTION_REMOVE_ITEM = "5";
    /** Item Menu option for returning to the Home Menu. */
    private static final String ITEM_OPTION_RETURN = "6";


    /*
     * HOME MENU OPTIONS
     * ---------------------------------------------------------------
    */

    /** Home Menu option for adding a new Item to the inventory. */
    private static final String HOME_OPTION_ADD_ITEM = "1";
    /** Home Menu option for searching for an Item by eiter itemNumber or itemDescription. */
    private static final String HOME_OPTION_SEARCH_OR = "2";
    /** Home Menu option for searching for an Item by both itemNumber and itemDescription. */
    private static final String HOME_OPTION_SEARCH_AND = "3";
    /** Home Menu option for displaying all items in the inventory along with their properties. */
    private static final String HOME_OPTION_SHOW_ALL = "4";



    /**
     * Launch the User Interface and activate the displaying of
     * the Home Menu.
     */
    public static void launch() {

        /* Prints the welcoming message to STDOUT. */
        printWelcome();

        boolean selected = false;
        String input = "";

        /* 
         * Asks for user input until the received input is
         * recgnized as one of the valid options for the
         * startup menu.
        */
        while (!selected) {
            printInitialOptions();

            input = scanner.next().trim();
    
            if (input.equals(OPTION_EXIT) 
                || input.equals(INIT_OPTION_ADD_ITEM) 
                || input.equals(INIT_OPTION_FILL_INVENTORY)
            ) {
                selected = true;
            }
        }
        
        /*
         * Identifies which option the user has selected and
         * redirects to the corresponding action.
        */
        switch (input) 
        {
            case OPTION_EXIT:   // User has chosen to exit.
                exit();
                break;
            case INIT_OPTION_ADD_ITEM:  // User will create a new item.
                addItem();
                break;
            case INIT_OPTION_FILL_INVENTORY:    // User will populate the warehouse with defaults.
                warehouse.fillInventory();
                itemMenu();
                break;
        }
    }


    /**
     * Displays the Item Menu to the console and waits for
     * the user to give an instruction.
     */
    private static void itemMenu() {
        /* Displays the current item and the options available. */
        printCurrentItem();
        printItemMenuOptions();

        Boolean selected = false;
        String input = "";

        /*
         * Awaits an input from the user which is recognized as
         * one of the valid options available for this menu.
         */
        while (!selected) {
            System.out.print("Your option: ");
            input = scanner.next().trim();

            switch (input) 
            {
                /*
                 * These are the available options for this menu.
                 */
                case OPTION_EXIT:               
                case ITEM_OPTION_INCREASE:      
                case ITEM_OPTION_DECREASE:      
                case ITEM_OPTION_CHANGE_PRICE:
                case ITEM_OPTION_ADD_DISCOUNT:
                case ITEM_OPTION_REMOVE_ITEM:
                case ITEM_OPTION_RETURN:
                    selected = true;
                    break;
                /* 
                 * Should the input not match the options above, the
                 * user will be informed and asked to try again.
                */
                default:
                    System.out.println("\nOption \"" + input + "\" is not available.");
                    System.out.println("Please try again.");
                    break;
            }
        }
        switch (input)
        {
            case OPTION_EXIT:  // User has chosen to exit the application.
                exit();
                break;
            case ITEM_OPTION_INCREASE:  // User wants to increase the amount of the item.
                increase();
                break;
            case ITEM_OPTION_DECREASE:  // User wants to decrease the amount of the item.
                decrease();
                break;
            case ITEM_OPTION_CHANGE_PRICE:  // User choses to change the item's price.
                changePrice();
                break;
            case ITEM_OPTION_ADD_DISCOUNT:  // User would like to add a discount to the item.
                addDiscount();
                break;
            case ITEM_OPTION_REMOVE_ITEM:   // User choses to remove the item from the registry.
                removeItem();
                break;
            case ITEM_OPTION_RETURN:    // User would like to return to the Home Menu.
                homeMenu();
                break;
        }
    }


    /**
     * Shows the Home Menu to the user and awaits for appropriate
     * input from the user. Upon receving valid option request,
     * the application will redirect to the corresponding action.
     */
    private static void homeMenu() {

        /* Displays the available options for the home menu and asks for input. */
        printHomeMenuOptions();

        Boolean selected = false;
        String input = "";

        /*
         * Awaits an input from the user which is recognized as
         * one of the valid options available for this menu.
         */
        while (!selected) {
            System.out.print("Your option: ");
            input = scanner.next().trim();
            
            switch (input)
            {
                case OPTION_EXIT:
                case HOME_OPTION_ADD_ITEM:
                case HOME_OPTION_SEARCH_OR:
                case HOME_OPTION_SEARCH_AND:
                case HOME_OPTION_SHOW_ALL:
                    selected = true;
                    break;
                default:
                    /* The input was not recognized as a valid option. */
                    System.out.println("\nOption \"" + input + "\" is not available.");
                    System.out.println("Please try again.");
                    break;
            }
        }
        switch (input)
        {
            case OPTION_EXIT:       // User has chosen to exit the program.
                exit();
                break;
            case HOME_OPTION_ADD_ITEM:  // User intends to add a new Item.
                addItem();
                break;
            case HOME_OPTION_SEARCH_OR:     // User wants to search by itemNumber or itemDescription.
                searchByNameOrDescription();
                break;
            case HOME_OPTION_SEARCH_AND:    // User wants to search by itemNumber and itemDescription.
                searchByNameAndDescription();
                break;
            case HOME_OPTION_SHOW_ALL:      // User would like to see all items in the warehouse.
                showAll();
                break;
        }
    }


    /**
     * Deletes a specified Item from the warehouse's inventory after
     * getting this action confirmed by the user.
     */
    private static void removeItem() {

        /* Asks user to confirm that the item are to be removed. */
        System.out.print("Are you sure you want to remove the item? (y/n):");
        boolean valid = false;
        boolean delete = false;
        String input = "";

        /*
         * Awaits for the user to either confirm or abort the attempt
         * of removing the current item from the warehouse.
         */
        while (!valid) {
            input = scanner.next().trim();

            if ("y".equalsIgnoreCase(input)) {          /* User confirms to removal by writing "y". */
                valid = true;
                delete = true;
            } else if ("n".equalsIgnoreCase(input)) {   /* User does not want to remove by writing "n". */
                valid = true;
                break;
            } else {    /* The input was not recognized as "y" or "n". */
                System.out.println("Input not recognized. Please enter 'y' for yes og 'n' for no.");
            }
        }

        if (delete) {   /* Deletion is chosen, item is deleted and user is redirected to home menu. */
            System.out.println("Item has been removed.");
            homeMenu();
        } else {        /* Delettion is aborted, and user is redirected to item menu. */
            System.out.println("Item will not be removed.");
            itemMenu();
        }
    }


    /**
     * Adds a discount to the current item determined by the user input.
     */
    private static void addDiscount() {

        /* Asks for user input as a positive integer between 0 and 100. */
        System.out.print("\nDiscount for item. Must be between 0 and 100: ");
        boolean valid = false;
        String input = "";
        int number = 0;

        /* Awaits valid user input. */
        while (!valid) {
            input = scanner.next().trim();
            try {
                /* Will try to convert the input into an integer. */
                number = Integer.parseInt(input);
                if (number >= 0 && number <= 100) { /* Input is a valid  */
                    valid = true;
                } else {    /* Input as int was not between 0 and 100. */
                    System.out.println("Please enter a number between 0 and 100");
                }
            } catch (NumberFormatException e) { /* Input was not an integer. */
                System.out.println("Please enter a valid number");
            }
        }
        int currentPrice = warehouse.getCurrentItem().getItemPrice();

        /* Sets the price to be the current price - the given percentage.*/
        warehouse.alterCurrentItemPrice((currentPrice * (1 - number/100)));
        System.out.println("\nPrice set to " + number + ".");

        /* Redirects to item menu. */
        itemMenu();
    }

    /**
     * Changes the price of the current item to a new price specified by the user.
     */
    private static void changePrice() {

        /* Asks for a new price from the user. */
        System.out.print("\nNew price: ");
        boolean valid = false;
        String input = "";
        int number = 0;

        /* Awaits a valid input which is a non-negative integer. */
        while (!valid) {
            input = scanner.next().trim();
            try {
                /* Converts the input to an integer. */
                number = Integer.parseInt(input);
                if (number >= 0) {  /* Ensures that the number is not negative. */
                    valid = true;
                } else { /* Number was not greater or equal to zero. */
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) { /* Input was not an integer. */
                System.out.println("Please enter a valid number");
            }
        }
        /* Changes the item price. */
        warehouse.alterCurrentItemPrice(number);
        System.out.println("\nPrice set to " + number + ".");

        /* Returns to item menu. */
        itemMenu();
    }


    /**
     * Allows for the user to increase the amount of the current item.
     */
    private static void increase() {

        /* Asks the user for how much to increase by. */
        System.out.print("\nHow much to increase by: ");
        boolean valid = false;
        String input = "";
        int number = 0;

        /* Awaits a valid user input. */
        while (!valid) {
            input = scanner.next().trim(); 
            try {
                /* Tries to parse input to integer. */
                number = Integer.parseInt(input);
                if (number >= 0) {  /* Number is not negative. */
                    valid = true;
                } else {    /* Number is not positive. */
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) { /* Input was not a number. */
                System.out.println("Please enter a valid number");
            }
        }
        /* Increases the item amount. */
        int newAmount = warehouse.getCurrentItem().getItemAmount() + number;
        warehouse.alterCurrentItemAmount(newAmount);
        System.out.println("\nItem amount increased by " + number + ".");

        /* Returns to the item menu. */
        itemMenu();
    }


    /**
     * Allows for the user to decrease the amount of the current item.
     */
    private static void decrease() {

         /* Asks the user for how much to increase by. */
        System.out.print("\nHow much to decrease by: ");
        boolean valid = false;
        String input = "";
        int number = 0;
        int currentAmount = warehouse.getCurrentItem().getItemAmount();

        /* Awaits a valid user input. */
        while (!valid) {
            input = scanner.next().trim();

            try {
                /* Tries to parse input to integer. */
                number = Integer.parseInt(input);
                if (number >= 0 && number <= currentAmount) { /* New price is not negative. */
                    valid = true;
                } else {    /* New price is negative. */
                    System.out.println("Not valid: " + number);
                    System.out.println("Remember to pass a positive integer, not being greater than the current amount.");
                }
            } catch (NumberFormatException e) { /* Input is not a valid number. */
                System.out.println("Please enter a valid number.");
            }
        }

        /* Decreases the amount of the item. */
        int newAmount = currentAmount - number;
        warehouse.alterCurrentItemAmount(newAmount);
        System.out.println("\nItem amount decreased by " + number + ".");

        /* Returns to item menu. */
        itemMenu();
    }


    /**
     * Get an iterator object for the LinkedHashSet containing the items
     * present in the register.
     * 
     * @param items the LinkedHashSet containing the items.
     * @return new iterator.
     */
    private static Iterator<Item> getIterator(LinkedHashSet<Item> items) {
        return items.iterator();
    }


    /**
     * Prints all items in the register to the console along with all their
     * field values.
     */
    private static void showAll() {

        /* Retrieves all items in the register along with an iterator. */
        LinkedHashSet<Item> items = warehouse.getItems();
        Iterator<Item> iterator = getIterator(items);
        
        /* Iterates over all items in the LinkedHashSet. */
        while (iterator.hasNext()) {
            String[] fields = iterator.next().getAllFields();

            /* Prints all field values for each Item. */
            for (String field : fields) {
                System.out.println(field);
            }
            System.out.println("\n");
        }
        
    }


    /**
     * Adds a new item which is to be stored in the warehouse registry. 
     * User will be asked to specify each value required for creating a
     * new instance of the Item. 
     */    
    private static void addItem() {

        boolean valid = false;

        String number = "";
        String brand = "";
        String color = "";
        String description = "";
        double weight = 0.0;
        double height = 0.0;
        double length = 0.0;
        int price = 0;
        int amount = 0;
        int category = 0;
        
        /*
         * Awaits valid inputs for each parameter.
         */
        while (!valid) {
            System.out.print("\nItem number: ");
            number = scanner.next().trim();
            System.out.print("\nItem brand: ");
            brand = scanner.next().trim();
            System.out.print("\nItem color: ");
            color = scanner.next().trim();
            System.out.print("\nShort description: ");
            description = scanner.next().trim();
            System.out.print("\nWeight in kilograms (double): ");
            String sweight = scanner.next().trim();
            System.out.print("\nLength in meters (double): ");
            String slength = scanner.next().trim();
            System.out.print("\nHeight in meters (double): ");
            String sheight = scanner.next().trim();
            System.out.print("\nPrice in NOK (int): ");
            String sprice = scanner.next().trim();
            System.out.print("\nAmount of items: ");
            String samount = scanner.next().trim();
            System.out.print("\nItem category: ");
            String scategory = scanner.next().trim();


            /*
             * Ensures that all parameters are of valid data types.
             */
            try {
                weight = Double.parseDouble(sweight);
                length = Double.parseDouble(slength);
                height = Double.parseDouble(sheight);

                price = Integer.parseInt(sprice);
                amount = Integer.parseInt(samount);
                category = Integer.parseInt(scategory);

                valid = true;
            } catch (Exception e) {
                System.out.print("There was something wrong with the inputs ");
                System.out.println("please try again.");
            }
        }

        /* Tries to add the new item. */
        boolean success = warehouse.addItem(
            new Item(
                    number, brand, color, 
                    description, weight, 
                    length, height, price, 
                    amount, category
                )
            );

        if (success) { /* Item was unique and is now added. */
            System.out.println("\n\nNew item was created.");
        } else { /* Item was already present. */
            System.out.println("\n\nItem already exists.");
        }
    }


    /**
     * Exits the application and terminates the program.
     */
    private static void exit() {
        System.out.println("\n Thank you for using Warehouse Management System");
        System.exit(0);
    }


    /**
     * Prints the welcome message to the user upon starting this application.
     */
    private static void printWelcome() { 
        System.out.println("\nWelcome to the Warehouse"                         );
        System.out.println("----------------------------\n\n"                   );
        System.out.println("Lets start with adding an item to your inventory:\n");
    }


    /**
     * Prints the avaliable options for the startup menu to STDOUT and
     * encourages the user to pick one of them to proceed.
     */
    private static void printInitialOptions() {
        System.out.println("   1. Add a new item to your inventory." );
        System.out.println("   2. Fill inventory with default items.");
        System.out.println("   0. Exit.\n"                           );

        System.out.println("Enter the number of desired operation."  );
    }

    /**
     * Displays the current item to the console along with the number,
     * amount and price of the item.
     */
    private static void printCurrentItem() {
        System.out.println("\nCurrent item:\n");

        System.out.println("Item number:" + warehouse.getCurrentItem().getItemNumber());
        System.out.println("Amount:"      + warehouse.getCurrentItem().getItemAmount());
        System.out.println("Price:"       + warehouse.getCurrentItem().getItemPrice() );
    }


    /**
     * Displays the available options in the item menu to the console.
     */
    private static void printItemMenuOptions() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("   1. Increase amount.");
        System.out.println("   2. Decrease amount.");
        System.out.println("   3. Change pricing. ");
        System.out.println("   4. Add discount.   ");
        System.out.println("   5. Remove item.    ");
        System.out.println("   6. Return."         );
        System.out.println("   0. Exit."           );
    }


    /**
     * Displays the options which the user can select when the application
     * is in its home menu.
     */
    private static void printHomeMenuOptions() {
        System.out.println("\nWhat would you like to do?");

        System.out.println("   1. Add a new item to your inventory."      );
        System.out.println("   2. Search for item (name or description). ");
        System.out.println("   3. Search for item (name and description).");
        System.out.println("   4. Show all items in the inventory."       );
        System.out.println("   0. Exit.");
    }


    /**
     * Prompts the user to enter a string which will be used by the warehouse
     * instance to search for a specific item by either number or description.
     */
    private static void searchByNameOrDescription() {
        System.out.print("\nPlease enter a search string:");

        /*
         * Parses a user input from STDIN and removes 
         * eventual spaces in front and after the input.
         */
        String search = scanner.next().trim();
        warehouse.search(search);

        /*
         * Checks if the search resulted in a found item
         * or if there were no matches.
         */
        if (warehouse.getCurrentItem() == null) {
            System.out.println("Did not find this item.");
            /* If not found, return to the home menu. */
            homeMenu();
        } else {
            System.out.println("Item found.");
            /* If found, display the Item in the item menu. */
            itemMenu();
        }
    }

    /**
     * Prompts the user to enter two different string which will be used by
     * the warehouse instance to search for a specific item by both its
     * number as well as its description.
     */
    private static void searchByNameAndDescription() {

        /*
         * Parses two separate user inputs from STDIN and 
         * removes eventual spaces in front and after each
         * of the inputs.
         */
        System.out.print("\nPlease enter a search string nr 1:");
        String search1 = scanner.next().trim();
        System.out.print("\nPlease enter a search string nr 2:");
        String search2 = scanner.next().trim();
        warehouse.search(search1, search2);

        /*
         * Checks if the search resulted in a found item
         * or if there were no matches.
         */
        if (warehouse.getCurrentItem() == null) {
            System.out.println("Did not find this item.");
            /* If not found, return to the home menu. */
            homeMenu();
        } else {
            System.out.println("Item found.");
            /* If found, display the Item in the item menu. */
            itemMenu();
        }
    }
}
