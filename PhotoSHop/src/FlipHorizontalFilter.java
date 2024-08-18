/**
 * Filter that flips the image horizontally. This class is COMPLETE. Don't
 * change it. But model your other classes (such as FlipVerticalFilter) after
 * it.
 */
/**
 * The FlipHorizontalFilter class implements the Filter interface
 * and flips the image horizontally.
 */
public class FlipHorizontalFilter implements Filter {
    /**
     * This method applies the horizontal flip filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    public void filter(PixelImage pi) {
        // Get the pixel data from the image.
        Pixel[][] data = pi.getData();

        // Loop through each row of the image.
        for (int row = 0; row < pi.getHeight(); row++) {
            // Loop through each column in the row up to the middle of the row.
            // This ensures we only swap each pair of pixels once.
            for (int col = 0; col < pi.getWidth() / 2; col++) {
                // Store the current pixel in a temporary variable.
                Pixel temp = data[row][col];
                
                // Swap the current pixel with its corresponding pixel on the opposite side of the row.
                data[row][col] = data[row][pi.getWidth() - col - 1];
                
                // Place the temporary pixel in the position of the opposite pixel.
                data[row][pi.getWidth() - col - 1] = temp;
            }
        }

        // Set the modified pixel data back to the image.
        pi.setData(data);
    }
}
