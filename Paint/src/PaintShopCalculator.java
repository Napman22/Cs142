/**
 * Computes the amount of paint needed to paint a room
 */

/**
 * Computes the amount of paint needed to paint a room
 */

public class PaintShopCalculator {

    // Constants
    // Prices of the paint containers in dollars
    public final double FIVEGALLONS = 124.00;
    public final double ONEGALLON = 25.10;
    public final double HALFGALLON = 13.00;
    public final double QUART = 6.70;
    public final double PINT = 3.95;
    public final double HALFPINT = 2.25;

    // Area that can be painted with one gallon of paint (in square inches)
    public final double AREA_PER_GALLON = 25000.0;

    // Room measurements
    private int heightFeet;
    private int heightInches;
    private int lengthFeet;
    private int lengthInches;
    private int widthFeet;
    private int widthInches;

    /**
     * Constructs a PaintShopCalculator object with room dimensions.
     *
     * @param heightFeet   Height of the room in feet.
     * @param heightInches Height of the room in inches.
     * @param lengthFeet   Length of the room in feet.
     * @param lengthInches Length of the room in inches.
     * @param widthFeet    Width of the room in feet.
     * @param widthInches  Width of the room in inches.
     */
    public PaintShopCalculator(int heightFeet, int heightInches, int lengthFeet, int lengthInches, int widthFeet,
            int widthInches) {
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.lengthFeet = lengthFeet;
        this.lengthInches = lengthInches;
        this.widthFeet = widthFeet;
        this.widthInches = widthInches;
    }
    /**
     * Calculates the total area to be painted.
     *
     * @return The total area to be painted in square inches.
     */
    private double calculateTotalArea() {
        double heightInInches = (heightFeet * 12) + heightInches;
        double lengthInInches = (lengthFeet * 12) + lengthInches;
        double widthInInches = (widthFeet * 12) + widthInches;
        return 2 * (lengthInInches * heightInInches + widthInInches * heightInInches) + lengthInInches * widthInInches;
    }
    

    /**
     * Computes the amount of paint needed and the total cost.
     *
     * @return A string containing the amount of paint needed and the cost breakdown.
     */
    public String toString() {
        // Calculate total area to be painted
        double totalArea = calculateTotalArea();
       
        // Calculate gallons of paint needed
        double gallonsNeeded = totalArea / AREA_PER_GALLON;
        double remainder;
        // Calculate number of each container needed
        int fiveGallons = (int) (gallonsNeeded / 5);
        remainder = gallonsNeeded%5;
        
        int oneGallon = (int) (remainder / 1);
        remainder -=oneGallon* 1;
        
        int halfGallon = (int) (remainder / 0.5);
        remainder -=halfGallon*0.5;
        
        int quart = (int)(remainder / 0.25);
        remainder -=quart* 0.25;
        
        int pint = (int) (remainder / 0.125); 
        remainder -= pint*0.125;
        
        int halfPint =(int) (remainder / 0.0625); 
        if(remainder != 0) 
		{
			halfPint +=1;
		}
       
        // Calculate total price
        double totalPrice = (fiveGallons * FIVEGALLONS) + (oneGallon * ONEGALLON) + (halfGallon * HALFGALLON)
                + (quart * QUART) + (pint * PINT) + (halfPint * HALFPINT);
        
        
       
        // Construct the result string
        StringBuilder result = new StringBuilder();
        result.append(String.format("For this job, you need %.3f gallons of paint.\n", totalArea / AREA_PER_GALLON));
        result.append("You will need to purchase\n");
        appendContainer(result, fiveGallons, "five-gallon");
        appendContainer(result, oneGallon, "one-gallon");
        appendContainer(result, halfGallon, "half-gallon");
        appendContainer(result, quart, "quart");
        appendContainer(result, pint, "pint");
        appendContainer(result, halfPint, "half-pint");
        result.append("\n");
        result.append(String.format("The total cost is $%.2f. \n",totalPrice));
        
        //Checks if discount can be applied(cents is odd and divisble by 5)
        int cents = (int) (Math.round(totalPrice * 100) % 100);
        String s = null;
        System.out.println(cents);
        System.out.println(totalPrice);
        double discount = totalPrice;
        if (cents % 2 != 0 && cents % 5 ==0) {
        	if (fiveGallons > 0) {
        		discount -= FIVEGALLONS;
        		s = "five-gallon container";
        	}
        	else if (oneGallon > 0) {
        		discount -= ONEGALLON;
        		s = "one-gallon container";
        	}
        	else if (quart > 0) {
        		discount -=  QUART;
        		s = "quart container";
        	}
        	else if (pint > 0) {
        		discount -= PINT;
        		s = "pint container";
        	}
        	else if (halfPint > 0) {
        		discount -= HALFPINT;
        		s = "half-pint container";
        	}
        	
        	
        	result.append(String.format("However, because the amount of cents (%dc) \n"
        			+ "in the cost is odd and divisible by 5,\n", cents));
        	result.append(String.format("you get 1 %s for free! \n", s));
        	result.append("\n");
        	result.append(String.format("\t Your total is $%.2f \n", discount));
        	result.append("\n");
        	result.append("\t Thank you for your business!");
        }

        return result.toString();
    }




    /**
     * Appends container information to the result string.
     *
     * @param result    The StringBuilder object to append to.
     * @param count     The number of containers needed.
     * @param container The type of container.
     */
    private void appendContainer(StringBuilder result, int count, String container) {
        if (count > 0) {
            result.append("\t").append(count).append(" ").append(container);
            if (count > 1) {
                result.append(" containers");
            } else {
                result.append(" container");
            }
            result.append("\n");
        }
        
    }
}
