// Name: Colin Eade
// Date: February 18, 2023
// App Name: Letter Grade Converter
// Description: App the takes a grade percentage and converts it to its equivalent letter grade

import java.util.Scanner;

public class LetterGradeConverter 
{
    // Constants
    static final String SET_TITLE = "\033]0%s\007";
    static final String CLEAR_TERMINAL = "\033c";
    static final String BANNER = """
    ======================
    Letter Grade Converter
    ======================
    """;

    // User input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        // Variables
        float      userGrade;
        String   letterGrade;
        String gradeFeedback;

        // While loop for restart functionality
        do
        {
            // Clear the terminal and print the banner
            System.out.println(CLEAR_TERMINAL + BANNER);

            // Set the title
            System.out.printf(SET_TITLE, "Letter Grade Converter - Colin Eade");

            // Recieves the user grade as an integer
            userGrade = Grade.getUserGrade();

            // Passes the integer grade through the function to get the letter grade
            letterGrade = Grade.letterGrade(userGrade);

            // Passes the letter grade through the function to get the feedback for the grade
            gradeFeedback = Grade.gradeFeedback(letterGrade);

            // Clear the terminal and print the banner
            System.out.println(CLEAR_TERMINAL + BANNER);

            System.out.printf("A grade of %s%% is equivalent to %s which is considered \"%s\"!\n\n", userGrade, letterGrade, gradeFeedback);

        } while(Grade.restartProgram());

        // Close the scanner
        Grade.endProgram();
    }

}