
// Name: Colin Eade
// Date: March 13, 2023
// App Name: Horse Race
// App Decription: App that simulates a horse race

import java.util.Scanner;

public class HorseRace
{   
    // Constants
    static final int HALF_SECOND = 500;
    static final String CLEAR_TERMINAL = "\033c";
    static final String SET_TITLE = "\033]0;%s\007";
    static final String WALL = "===================+===============";
    static final String FINISH_FLAG ="""
                       |> FINISH
                       |        
    """;
    static final String BANNER = """
    ==========
    HORSE RACE
    ==========        
    """;


    /**
     * Stops code execution for half a second
     */
    public static void waitHalfSecond()
    {
        try 
        {
            Thread.sleep(HALF_SECOND);
        } 
        catch (Exception exception)
        {
        }
    }

    /**
     * Draws the distance a horse has covered
     * @param horse The horse we are getting the distance for
     */
    public static void drawDistanceLine(Horse horse)
    {   
        // Variables
        String distance = "";
        int length = horse.getDistanceCovered();

        // Writes a "." for the distance a horse has covered E.g. a horse with distanceCovered = 5 ----> "....."
        for (int count = 0; count < length; count++)
            distance += ".";

        // A length of 20 is the win condition for the race
        if (length < 20)
        {    
            // if less than 20 then print the horses name in front of the distance dots
            System.out.println(distance + horse.getName());
        }
        else
        {   
            // Once the length hits 20 add "Wins!" after the horse's name
            System.out.println(distance + horse.getName() + " Wins!");
        }
    }


    public static void main(String[] args) 
    {   
        // User input
        Scanner scanner = new Scanner(System.in);

        // Array of Horses
        Horse[] horses = 
        {
            new Horse("Lucky Day"),
            new Horse("Silver Dollar"),
            new Horse("Chicago's Defense"),
            new Horse("Old Glory"),
            new Horse("The Count")
        };

        // Variables
        boolean Winners = false;

        // Set title
        System.out.printf(SET_TITLE, "Horse Race - Colin Eade");

        // Initial Screen
        // Print Banner
        System.out.println(BANNER);

        // Press [enter] to start race
        System.out.print("Press [enter] to start the race!: ");
        scanner.nextLine();

        // While loop that runs until a win condition is met
        while(!Winners)
        {   
            // Clear terminal
            System.out.print(CLEAR_TERMINAL);

            // Print Banner
            System.out.println(BANNER);

            // Print finish flag
            System.out.print(FINISH_FLAG);

            // Print top wall
            System.out.println(WALL);

            // Race logic
            // Loop through the array of horse objects
            for (int index = 0; index < horses.length; index++)
            {   
                // Check for a winner upon each loop
                // The win will break out of the loops and display the "Wins!" text due to the functionality in the drawDistanceLine method
                if (horses[index].getDistanceCovered() == 20)
                {
                    Winners = true;
                }
                // Record the horse's distance (dependant on distanceCovered in Horse class)
                drawDistanceLine(horses[index]);

                // Use the run() function on each horse in the array (gives the horse a 50% chance to gain +1 on the distanceCovered)
                horses[index].run();
            }

            // Print bottom wall
            System.out.println(WALL);
            
            // Wait half a second before running through the loop again
            waitHalfSecond(); 
        }

        // Exit prompt
        System.out.print("\nPress [enter] to exit: ");
        scanner.nextLine();
        scanner.close();
    }
}