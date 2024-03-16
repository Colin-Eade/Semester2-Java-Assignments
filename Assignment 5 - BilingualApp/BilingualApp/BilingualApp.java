// Name: Colin Eade
// Date: April 18, 2023
// App Name: Bilingual App
// Description: App that allows the user to pick between English and French language on the GUI then allows them to register to a DC program

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class BilingualApp
{
    // Components
    static GridBagConstraints gridbag;
    static JFrame window;
    static JPanel panel;
    static JLabel bannerLabel;
    static JLabel studentNumLabel;
    static JSpinner studentNumSpinner;
    static JLabel nameLabel;
    static JTextField nameTextField;
    static JLabel programLabel;
    static JTextField programTextField;
    static JButton loadButton, registerButton;

    // Variables
    static String registerWarningMessage, registerWarningTitle, registerCompleteMessage, registerCompleteTitle, cannotWriteToFileMessage, cannotWriteToFileTitle, noStudentRecordsMessage, noStudentRecordsTitle;

    /** Opens an option menu that allows the user to choose between English and French
     * @return Returns the English or French language file
     */
    static File languageSelection()
    {
        // Constants
        final int ENGLISH = 0, FRENCH = 1;

        // Variables
        File file;

        // Language options
        String[] options = {"English", "Français"};
        
        // Option pane showing dialogue options
        int optionSelection = JOptionPane.showOptionDialog(null, "Please Choose Your Language: | Veuillez choisir votre langue :", "Language Selection | Sélection de langue", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        // If the selection is English OR the user closes the popup
        if (optionSelection == ENGLISH || optionSelection == -1)
        {
            // Open english.lang
            file = new File("english.lang");
        }
        // If the selection is French
        else if (optionSelection == FRENCH)
        {
            // open french.lang
            file = new File("french.lang");
        }
        // Should not get here but forced it to english just in case
        else
        {
            file = new File("english.lang");
        }

        // return the file
        return file;
    }

    /** initializes all componenets and messages of the GUI
     * @param file The language file from the user choice
     */
    static void InitializeGUI(File file)
    {
        // Variables
        File languageFile = file;
        Scanner fileScanner;

        try 
        {
            // Scan the .lang file passed through the function
            fileScanner = new Scanner(languageFile, "UTF-8");

            // Initialize the labels and buttons

            // Window
            window = new JFrame(fileScanner.nextLine());
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Closes when clicking the (x)
            window.setIconImage(new ImageIcon("registrationicon.png").getImage()); // Change the icon
            window.setResizable(false);     // Window is not resizable
            window.setLocationRelativeTo(null);     // Center window

            // -----------------------------------------------------------------------

            // Panel
            panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            gridbag = new GridBagConstraints(); // Where to put the components

            // -----------------------------------------------------------------------

            // Banner
            bannerLabel = new JLabel(fileScanner.nextLine());
            bannerLabel.setFont(new Font(null, 0, 24));

            // -----------------------------------------------------------------------

            // Student Number Spinner
            studentNumLabel = new JLabel(fileScanner.nextLine());
            studentNumSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999999999, 1));

            // -----------------------------------------------------------------------

            // Name TextField
            nameLabel = new JLabel(fileScanner.nextLine());
            nameTextField = new JTextField();
            nameTextField.setColumns(40);

            // -----------------------------------------------------------------------

            // Program TextField
            programLabel = new JLabel(fileScanner.nextLine());
            programTextField = new JTextField();
            programTextField.setColumns(40);

            // -----------------------------------------------------------------------

            // Load Button
            loadButton = new JButton(fileScanner.nextLine());
            loadButton.addActionListener(event -> loadClick());

            // -----------------------------------------------------------------------

            // Register Button
            registerButton = new JButton(fileScanner.nextLine());
            registerButton.addActionListener(event -> registerClick());
        
            // -----------------------------------------------------------------------

            // Initilize Messages
            registerWarningMessage = fileScanner.nextLine();
            registerWarningTitle = fileScanner.nextLine();
            registerCompleteMessage = fileScanner.nextLine();
            registerCompleteTitle = fileScanner.nextLine();
            cannotWriteToFileMessage = fileScanner.nextLine();
            noStudentRecordsMessage = fileScanner.nextLine();
            cannotWriteToFileTitle = fileScanner.nextLine();
            noStudentRecordsTitle = cannotWriteToFileTitle;

            // Close the scanner
            fileScanner.close();
        } 
        // Should never get here but just in case
        catch (Exception e) 
        {
            // Fatal error message
            JOptionPane.showMessageDialog(window,
            "An error occured while initializing the application | Une erreur s'est produite lors de l'initialisation de l'application",
            "Fatal Error! | Erreur Fatale !", JOptionPane.ERROR_MESSAGE);

            // Force the app to close
            System.exit(1);
        }

    }

    /** Executed when the [Register] button is clicked
     * Show an error if the user does not have a name or program
     * Save the student in a data file
     */
    static void registerClick()
    {
        // Variables
        String fileName;
        File file;
        FileWriter fileWriter;

        // Extract Student number, name, and Program from GUI
        String name = nameTextField.getText();
        int studentNumber = (int)studentNumSpinner.getValue();   // Explicit conversion
        String program = programTextField.getText();

        // Warning in case the student does enter all fields
        if (name.equals("") || program.equals(""))
        {
            JOptionPane.showMessageDialog(window, registerWarningMessage, registerWarningTitle, JOptionPane.WARNING_MESSAGE);
        }

        // Save the student in a file
        else
        {
            // Format the file name
            fileName = studentNumber + ".data";

            // Create a new file
            file = new File(fileName);

            try 
            {
                // Prepare to write in the file
                fileWriter = new FileWriter(file);

                // Start writing in the file
                fileWriter.write(studentNumber + "\n" + name + "\n" + program);

                // Finished writing, close the file
                fileWriter.close();

                // Tell the user that the character was saved in the file.save
                JOptionPane.showMessageDialog(window, registerCompleteMessage + fileName, registerCompleteTitle, JOptionPane.INFORMATION_MESSAGE);
            } 

            // Error incase we cannot write in the file
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(window, cannotWriteToFileMessage + fileName, cannotWriteToFileTitle, JOptionPane.ERROR_MESSAGE);
            }

            // Clear the GUI
            nameTextField.setText("");                  // Clear the name
            studentNumSpinner.setValue(0);          // Reset to 0
            programTextField.setText("");               // Clear program field
        }
    }

    static void loadClick()
    {
        // Variables
        String fileName;
        File file;
        Scanner fileScanner;
        boolean studentFound = false;

        // Student number from GUI
        int studentNumber = (int)studentNumSpinner.getValue();

        // Create file name
        fileName = studentNumber + ".data";

        // Create file object
        file = new File(fileName);

        try
        {
            // A scanner to read from files
            fileScanner = new Scanner(file, "UTF-8");

            // Grab first line (where the Student number is)
            int fileStudentNumber = Integer.parseInt(fileScanner.nextLine());

            // if the user input of the spinner matches the first line
            if (studentNumber == fileStudentNumber)
            {
                // Student found
                studentFound = true;

                // Set name textfield to next line on file (name line)
                nameTextField.setText(fileScanner.nextLine());

                // Set program text field to next line on file (program line)
                programTextField.setText(fileScanner.nextLine());

                // Close Scanner
                fileScanner.close();
            }
            else
            {
                // Student not found
                studentFound = false;
            }

        }
        catch (Exception e)
        {
            studentFound = false;
        }
        // If a student is not found
        if (!studentFound)
        {
            // Error message
            JOptionPane.showMessageDialog(window, noStudentRecordsMessage + studentNumber, noStudentRecordsTitle, JOptionPane.ERROR_MESSAGE);

            // Clear the GUI
            nameTextField.setText("");                  // Clear the name
            studentNumSpinner.setValue(0);          // Reset to 0
            programTextField.setText("");               // Clear program field
        }
    }

    public static void main(String[] args) 
    {   
        // Variables
        File languageFile;

        // Change the look and feel
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        } 
        catch (Exception e) 
        {
        }

        // -----------------------------------------------------------------------

        // Language Option
        languageFile = languageSelection();

        // -----------------------------------------------------------------------

        // Initialize GUI
        InitializeGUI(languageFile);

        // -----------------------------------------------------------------------

        // Place the components
        window.add(panel);

        // Banner
        gridbag.gridy = 0;
        gridbag.gridx = 0;
        panel.add(bannerLabel, gridbag);

        // Student Number Label
        gridbag.anchor = GridBagConstraints.WEST;
        gridbag.gridy = 1;
        gridbag.insets = new Insets(5, 0, 0, 0);
        panel.add(studentNumLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // Student Number Spinner
        gridbag.fill = GridBagConstraints.NONE;
        gridbag.ipadx = 145;
        gridbag.gridy = 2;
        panel.add(studentNumSpinner, gridbag);
        gridbag.ipadx = 0;

        // Load Button
        gridbag.anchor = GridBagConstraints.EAST;
        panel.add(loadButton, gridbag);
        gridbag.fill = GridBagConstraints.HORIZONTAL;

        // Name Label
        gridbag.fill = GridBagConstraints.HORIZONTAL;
        gridbag.anchor = GridBagConstraints.WEST;
        gridbag.gridy = 3;
        gridbag.insets = new Insets(5, 0, 0, 0);
        panel.add(nameLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // Name Textfield
        gridbag.gridy = 4;
        panel.add(nameTextField, gridbag);

        // Program Label
        gridbag.gridy = 5;
        gridbag.insets = new Insets(5, 0, 0, 0);
        panel.add(programLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // Program Textfield
        gridbag.gridy = 6;
        panel.add(programTextField, gridbag);

        // Register Button
        gridbag.gridy = 7;
        gridbag.insets = new Insets(5, 0, 0, 0);
        panel.add(registerButton, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);

        // -----------------------------------------------------------------------

        // Display the window
        window.pack();
        window.setVisible(true);
    }
}