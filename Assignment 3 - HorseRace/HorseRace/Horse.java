import java.util.Random;

public class Horse 
{
    // Variables
    private Random random = new Random();
    private String name;
    private int distanceCovered = 0;
    
    /**
     * Constructor Method
     * @return a new Horse object
     */
    Horse(String name)
    {
        // Initialize the class variables
        this.name = name;
    }

    /**
     * Gets the name of the horse
     * @return the horse name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the distance covered of a horse
     * @return the distance covered
     */
    public int getDistanceCovered()
    {
        return distanceCovered;
    }

    /**
     * Makes the horse run at a random speed
     */
    public void run()
    {   
        // Has a 50% chance to add +1 to the distanceCovered
        distanceCovered += random.nextInt(2);
    }
}
