import java.util.ArrayList;

public class PrestoCard 
{

    // Variables
    private double balance;
    private String name;
    private String history = "";

    /**
     * Constructor
     * @param balance
     * @param name
     * @return a new PrestoCard object
     */
    PrestoCard(double balance, String name) 
    {
        this.balance = balance;
        this.name = name;
    }


    // GETTERS
    // --------------------------------------------

    /**
     * @return The card balance
     */
    double getBalance() 
    {
        return balance;
    }

    /**
     * @return The name of the card holder
     */
    String getName() 
    {
        return name;
    }
    
    // --------------------------------------------

    /**
     * Subtracts a fare fee from the card balance if there are sufficient funds
     * @param fare The fare cost
     * @return True or False depending on if the balance is sufficient
     */
    public boolean tap(double fare)
    {
        // If there is enough balance to pay the fare
        if (balance >= fare)
        {
            // Subtract fare from card balance
            balance -= fare;

            // Concatenate action and the new balance to the history string for the card
            history += "Tap   - New balance: " + balance + "\n";
            return true;
        }
        else 
        {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    /**
     * Adds a fund amount provided by the user to the card balance
     */
    public void topUp(double funds)
    {
        // Add funds to the card balance
        balance += funds;

        // Concatenate action and the new balance to the history string for the card
        history += "Topup - New balance: " + balance + "\n";
    }

    /**
     * Iterates through an ArrayList of PrestoCards and finds a match of name on a card to a name provided by the user
     * @param cards The PrestoCard ArrayList
     * @param name The possible name grabbed from the scanner
     * @return the index of the PrestoCard within the list
     */
    static int searchName(ArrayList<PrestoCard> cards, String name)
    {   
        // Variables
        int index = 0;      // An index number that counts up for each iteration of the loop

        for (PrestoCard card : cards) 
        {   
            // Starts at 1 for first iteration
            // This ensures that if we do find a matching name the program can follow the same logic as if we used the ID number
            // add 1 for each iteration
            index++;
            if (card.getName().equalsIgnoreCase(name))      // iterate through the list until we find a match
            {
                return index;                               // return the index number if there is a match
            }
        }

        // Only get here if we went through the whole ArrayList without finding a match
        // Make index = -1 to ensure that it will be invalid when subsequent validation functions are ran on it
        return index = -1;     
    }

    /**
     * Prints the history of all transactions on a specific PrestoCard
     */
    public void printHistory()
    {
        // Variables
        String header = name + "'s Cards History";
        String line = "";
        String banner;

        // Make the lines as long as the header length
        for (int count = 0; count < header.length(); count++) 
        {
            line += "=";
        }

        // Formatted banner
        banner = line + "\n" + header + "\n" + line + "\n";

        // print the banner
        System.out.print(banner);

        // If there is no transaction history the string will be empty
        if (history.equals(""))
        {
            // Print "No history"
            System.out.print("\nNo history\n");
        }
        
        // If the string is not empty then that means there is history
        else 
        {
            // Print the history string
            System.out.print("\n" + history);
        }

        // Inform the user to go back to menu
        System.out.print("\nPress [enter] to return to the Main Menu: ");
    }
}
