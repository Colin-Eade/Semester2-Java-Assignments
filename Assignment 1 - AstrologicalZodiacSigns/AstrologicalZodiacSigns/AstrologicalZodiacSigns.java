// Name: Colin Eade
// Date: January 28, 2022
// App Name: Astrological Zodiac Signs
// Description: App that determines a user's Zodiac sign depending on the birth date they input

import java.util.Scanner;

public class AstrologicalZodiacSigns
{
    // Constants
    static final String SET_TITLE = "\033]0;%s\007";
    static final String CLEAR_TERMINAL = "\033c";
    static final int MIN_MONTH = 1;
    static final int MAX_MONTH = 12;
    static final int MIN_DAY = 1;
    static final int MAX_DAY = 31;

    public static void main(String[] args) 
    {
        // User input
        Scanner scanner = new Scanner(System.in);

        // Variables
        boolean valid = false;
        int month = 0;
        int day = 0;
        int date = 0;
        
        // Set the title
        System.out.printf(SET_TITLE, "Astrological Zodiac Signs - Colin Eade");

        // Print the ASCII art
        System.out.println(CLEAR_TERMINAL + Art.TITLE_BANNER);

        // Info text
        System.out.println("This program will tell your Astrological Zodiac Sign based on your birthday!\n");

        // Validation while loop
        do
        {   
            // Ask for the birthday
            System.out.print("Enter your birthday in numeric format [month] [day]: ");

            try
            {
                // Ensure the day and month are numeric
                month = scanner.nextInt();
                day = scanner.nextInt();
                valid = true;
            }

            catch(Exception exception)
            {
                // The input is not valid if it is not numeric
                valid = false;
            }

            // Get rid of leftover input
            scanner.nextLine();

            // Error message if not numeric
            if(!valid)
                System.out.println("Error - Dates must be in numeric format, [month] [day]! e.g. 10 31\n");

            // Error message if input values are not in the valid month AND day range
            else if((month < MIN_MONTH || month > MAX_MONTH) && (day < MIN_DAY || day > MAX_DAY))
            {
                valid = false;
                System.out.printf("Error - Your month input must fall between %s and %s. Your day input must fall between %s and %s\n\n"
                , MIN_MONTH, MAX_MONTH, MIN_DAY, MAX_DAY);
            }
            // Error message if input value is not in the valid month range
            else if(month < MIN_MONTH || month > MAX_MONTH)
            {
                valid = false;
                System.out.printf("Error - Your month input must fall between %s and %s\n\n", MIN_MONTH, MAX_MONTH);
            }
            // Error message if input value is not in the valid day range
            else if(day < MIN_DAY || day > MAX_DAY)
            {
                valid = false;
                System.out.printf("Error - Your day input must fall between %s and %s\n\n", MIN_DAY, MAX_DAY);
            }
                
        } while(!valid);
        
        // Input has passed validation

        // Clear the terminal and print the title banner again
        System.out.println(CLEAR_TERMINAL + Art.TITLE_BANNER);

        // Print statement with user date information
        System.out.printf("Your Astrogical Zodiac sign based on your birthday (%s/%s):\n\n", month, day);

        // Math to make user input date easier to work with
        date = month * 100 + day;

        if (321 <= date && date <= 419) // March 21 – April 19
        {
            System.out.println(Art.ARIES);
        }
        else if (420 <= date && date <= 520) // April 20 – May 20
        {
            System.out.println(Art.TAURUS);
        }
        else if (521 <= date && date <= 620) // May 21 – June 20
        {
            System.out.println(Art.GEMINI);
        }
        else if (621 <= date && date <= 722) // June 21 – July 22
        {
            System.out.println(Art.CANCER);
        }
        else if (723 <= date && date <= 822) // July 23 – August 22
        {
            System.out.println(Art.LEO);
        }
        else if (823 <= date && date <= 922) // August 23 – September 22
        {
            System.out.println(Art.VIRGO);
        }
        else if (923 <= date && date <= 1022) // September 23 – October 22
        {
            System.out.println(Art.LIBRA);
        }
        else if (1023 <= date && date <= 1121) // October 23 – November 21
        {
            System.out.println(Art.SCORPIO);
        }
        else if (1122 <= date && date <= 1221) // November 22 – December 21
        {
            System.out.println(Art.SAGITTARIUS);
        }
        else if (1222 <= date || date <= 119) // December 22 – January 19
        {
            System.out.println(Art.CAPRICORN);
        }
        else if (120 <= date && date <= 218) // January 20 – February 18
        {
            System.out.println(Art.AQUARIUS);
        }
        else if (219 <= date && date <= 320) // February 19 – March 20
        {
            System.out.println(Art.PISCES);
        }

        // Exit prompt
        System.out.print("\nPress [enter] to exit: ");
        scanner.nextLine();
        scanner.close();
    }   
}
