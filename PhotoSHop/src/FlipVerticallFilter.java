/**
 * Filter that flips the image vertically. This class is COMPLETE. 
 * Don't change it. But model your other classes (such as FlipVerticalFilter) after it.
 */
public class FlipVerticallFilter implements Filter {
    /**
     * This method applies the vertical flip filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    public void filter(PixelImage pi) {
        // Get the pixel data from the image.
        Pixel[][] data = pi.getData();
        
        // Create a new array to store the flipped image data.
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Loop through each row of the image.
        for (int row = 0; row < pi.getHeight(); row++) {
            // Loop through each column in the current row.
            for (int col = 0; col < pi.getWidth(); col++) {
                // Calculate the new position of the pixel for the vertical flip.
                newData[pi.getHeight() - row - 1][col] = data[row][col];
            }
        }

        // Set the modified pixel data back to the image.
        pi.setData(newData);
    }
}
