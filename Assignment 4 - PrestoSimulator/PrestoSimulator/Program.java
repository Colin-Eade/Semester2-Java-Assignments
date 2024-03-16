// Name: Colin Eade
// Date: March 25, 2023
// App Name: Presto Simulator
// Description: App that simulates a Presto card system. Includes functions like topping up the card, tapping and getting the balance 

import java.util.ArrayList;
import java.util.Scanner;

public class Program
{
    // Constants
    static final int ONE_SECOND = 1000;
    static final double FARE = 2.50;
    static final String SET_TITLE = "\033]0;%s\007";
    static final String CLEAR_TERMINAL = "\033c";
    static final String BANNER = """
    ===========
    PRESTO CARD
    ===========

    Program that simulates Presto cards
    """;
    
    /** 
     * Wait one second
     */
    static void waitAsec()
    {
        try
        {
            Thread.sleep(ONE_SECOND);
        }
        catch(Exception exception)
        {
        }
    }

    /**
     * Gets the index from a PrestoCard ArrayList of an input passed through it
     * Can accept strings and numerical characters
     * Can find index via number ID or cardholder name
     * Uses the searchName() function in the PrestoCard class to be able to get index by name
     * @param list The PrestoCard ArrayList created in the app
     * @param input The user input that is grabbed by the scanner
     * @return An integer value that corresponds to index to a specific card in the PrestoCard ArrayList
     */
    static int getIndex(ArrayList<PrestoCard> list, String input)
    {
        // Variables
        String user_input = input;      // The input from the user grabbed by the scanner
        int index = 0;                  // The index number to be returned
        boolean numeric;                // Boolean to check if input was an integer. If not it will pass the input through the searchName() function
        
        // Check if input is an integer
        try
        {
            index = Integer.parseInt(user_input);
            numeric = true;
        }

        // Catch if not integer
        catch (Exception e)
        {
            numeric = false;
        }
        
        // if the input wasn't an int then try to search by name in the array to find the index
        if (!numeric)
        {
            index = PrestoCard.searchName(list, user_input);
        }

        // Convert from ID to ArrayList index
        index--;
        return index;
    }

    public static void main(String[] args) 
    {
        // User input
        Scanner scanner = new Scanner(System.in);

        // ArrayList of PrestoCard objects
        ArrayList<PrestoCard> cards = new ArrayList<>();
        cards.add(new PrestoCard(5.00, "Fred"));
        cards.add(new PrestoCard(15.00, "Lilly"));
        cards.add(new PrestoCard(2.00, "Jim"));

        // Variables
        boolean running = true;
        boolean numeric;
        String command;
        double balance = 0;
        int index = 0;
        double funds = 0;

        // Set title
        System.out.printf(SET_TITLE, "Presto Simulator - Colin Eade");

        do {
            // Clear the terminal and print banner
            System.out.println(CLEAR_TERMINAL + BANNER);

            // State the amount of cards
            System.out.println("We currently have " + cards.size() + " cards!\n");

            // Print all cards
            for (int count = 0; count < cards.size(); count++)
            {
                // ID - NAME - BALANCE
                System.out.printf("%s - %s - Balance: $ %.2f\n", count + 1, cards.get(count).getName(), cards.get(count).getBalance());
            }

            // Ask for a command
            System.out.print("\nEnter a command: ");
            command = scanner.next(); // Get one word

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Quit the app
            if (command.equalsIgnoreCase("quit"))
            {
                running = false;
                System.out.println("Goodbye");
            }

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Add a card holder and balance
            else if (command.equalsIgnoreCase("add"))
            {
                // Get name and balance
                command = scanner.next();
                try 
                {
                    balance = scanner.nextDouble();
                    numeric = true;
                }
                catch(Exception e)
                {
                    numeric = false;
                }

                // Error if balance is not numeric
                if (!numeric)
                {
                    System.out.println("Error - Balance must be numeric");
                }

                // Valid name and balance, create the card
                else
                { 
                    cards.add(new PrestoCard(balance, command));
                }
            }

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Delete a card entry
            else if (command.equalsIgnoreCase("del"))
            {
                // Get an input
                // Can be a number or characters
                command = scanner.next();

                // Get the index of the user input
                index = getIndex(cards, command);

                // Error in case ID is out of bounds
                if (index >= cards.size() || index < 0)
                {
                    System.out.println("Error - Invalid ID");
                }

                // Valid index, delete card
                else 
                {
                    cards.remove(index);
                }
            }

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Tap card
            else if (command.equalsIgnoreCase("tap"))
            {
                // Get an input
                // Can be a number or characters
                command = scanner.next();

                // Get the index of the user input
                index = getIndex(cards, command);

                // Error in case ID is out of bounds
                if (index >= cards.size() || index < 0)
                {
                    System.out.println("Error - Invalid ID");
                }

                // Valid index, tap card
                else 
                {
                    cards.get(index).tap(FARE);
                }
            }

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Topup card
            else if (command.equalsIgnoreCase("topup"))
            {
                // Get an input
                // Can be a number or characters
                command = scanner.next();

                // Get the index of the user input
                index = getIndex(cards, command);

                // Error in case ID is out of bounds
                if (index >= cards.size() || index < 0)
                {
                    System.out.println("Error - Invalid ID");
                }

                // Funds validation
                try
                {
                    funds = scanner.nextDouble();
                    numeric = true;
                }
                catch (Exception e)
                {
                    numeric = false;
                }

                // Error if funds are not numeric or <= 0
                if (!numeric || funds <= 0)
                {
                    System.out.println("Error - The funds must be a number greater than 0");
                }

                else
                {
                    cards.get(index).topUp(funds);
                }
            }

            // ------------------------------------------------------------------------------------------------------------------------------------

            // Print history
            else if (command.equalsIgnoreCase("history"))
            {
                // Get an input
                // Can be a number or characters
                command = scanner.next();

                // Get the index of the user input
                index = getIndex(cards, command);

                // Error in case ID is out of bounds
                if (index >= cards.size() || index < 0)
                {
                    System.out.println("Error - Invalid ID");
                }

                else
                {
                    // Clear the terminal
                    System.out.print(CLEAR_TERMINAL);

                    // Print history string for the card (includes the press [enter] to continue string)
                    cards.get(index).printHistory();

                    // Grabs user input when they press enter
                    scanner.nextLine();
                }
            }
            else
            {
                System.out.println("Error - Invalid command");
            }

            // Get rid of any leftover input
            scanner.nextLine();

            // Wait 1 sec
            waitAsec();

        } while (running);

        // Close the app
        scanner.close();
    }
}