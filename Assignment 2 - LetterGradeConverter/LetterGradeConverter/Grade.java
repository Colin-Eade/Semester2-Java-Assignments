import java.util.Scanner;

public class Grade 
{   
    // Constants
    static private final int MIN = 0,   MAX = 100;
    static private final String A_PLUS      = "A+";
    static private final String A           = "A";
    static private final String A_MINUS     = "A-";
    static private final String B_PLUS      = "B+";
    static private final String B           = "B";
    static private final String B_MINUS     = "B-";
    static private final String C           = "C";
    static private final String D_PLUS      = "D+";
    static private final String D_MINUS     = "D-";
    static private final String F           = "F";

    // User input
    static private Scanner scanner = new Scanner(System.in);

    /** End the program by closing the scanner */
    static public void endProgram()
    {
        scanner.close();
    }

    /** Restart functionality
     * @return true for restart, false for exit
     */
    static public boolean restartProgram()
    {
        System.out.print("Enter [R] to restart or any other key to exit: ");
        return scanner.nextLine().toLowerCase().equals("r");
    }

    /** Gets the user grade as a string and converts it to an int
     * Will loop until a valid input is given
     * @return A numeric grade as an integer
     */
    static public float getUserGrade()
    {
        // Variables
        float userGrade;

        // Validation loop for user input
        do 
        {
            try 
            {   
                // Get user input
                System.out.print("Enter your grade percentage: ");

                // Allow them to input a floating point (decimal number)
                userGrade = Float.parseFloat(scanner.nextLine());

                // validate that the input is between 
                if (MIN <= userGrade && userGrade <= MAX)

                    // The input it valid - break out of the validation loop
                    break;

                // Inform user of error and restart the loop
                else
                {
                    System.out.printf("Error - Your grade percentage must be between %s and %s\n\n", MIN, MAX);
                }
            }
            // Catches non-numeric inputs and restarts the loop
            catch (Exception exception)
            {
                System.out.println("Error - Input must be a numeric\n");
            }
        } while(true);
        
        // Returns the valid user input as an integer
        return userGrade;
    }


    /** Takes the users numeric grade and returns the equivalent letter grade
     * @param gradePercentage The user's grade in numeric format
     * @return The equivalent letter grade
     */
    static public String letterGrade(Float gradePercentage)
    {       
        // Variables
        int integerGrade;

        // Convert to integer
        integerGrade = Math.round(gradePercentage);

        // Switches the letter grade based on the integer range
        if (90 <= integerGrade && integerGrade <= 100)        // "A+"
        {
            return A_PLUS;
        }
        else if (85 <= integerGrade && integerGrade <= 89)    // "A"
        {
            return A;
        }
        else if (80 <= integerGrade && integerGrade <= 84)    // "A-"
        {
            return A_MINUS;
        }
        else if (75 <= integerGrade && integerGrade <= 79)    // "B+"
        {
            return B_PLUS;
        }
        else if (70 <= integerGrade && integerGrade <= 74)    // "B"
        {
            return B;
        }
        else if (65 <= integerGrade && integerGrade <= 69)    // "B-"
        {
            return B_MINUS;
        }
        else if (60 <= integerGrade && integerGrade <= 64)    // "C"
        {
            return C;
        }
        else if (55 <= integerGrade && integerGrade <= 59)    // "D+"
        {
            return D_PLUS;
        }
        else if (50 <= integerGrade && integerGrade <= 54)    // "D-"
        {
            return D_MINUS;
        }
        else if (0 <= integerGrade && integerGrade <= 49)     // "F"
        {
            return F;
        }
        else
        {
            return null;
        }
    }

    /** Takes the letter grade and provides feedback based on it
     * @param letterGrade The letter grade
     * @return The feedback based on the letter grade
     */
    static public String gradeFeedback(String letterGrade)
    {
    // Switches the feedback based on the letter grade passed through
    switch (letterGrade)
        {
            case A_PLUS:  return "outstanding";
            case A:       return "exemplary"; 
            case A_MINUS: return "excellent";
            case B_PLUS:  return "very good";
            case B:       return "good";
            case B_MINUS: return "satisfactory";
            case C:       return "acceptable";
            case D_PLUS:  return "conditional pass";
            case D_MINUS: return "conditional pass";
            case F:       return "fail";
            default:      return null;
        }
    }

}   
