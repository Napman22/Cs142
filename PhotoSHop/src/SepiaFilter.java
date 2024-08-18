/**
 * The SepiaFilter class implements the Filter interface
 * and applies a sepia tone effect to the image.
 */
public class SepiaFilter implements Filter {
    /**
     * This method applies the sepia filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Get the pixel data from the image.
        Pixel[][] data = pi.getData();

        // Loop through each row of the image.
        for (int row = 0; row < pi.getHeight(); row++) {
            // Loop through each column in the current row.
            for (int col = 0; col < pi.getWidth(); col++) {
                // Get the current pixel.
                Pixel p = data[row][col];

                // Get the original red, green, and blue values of the pixel.
                int red = p.red;
                int green = p.green;
                int blue = p.blue;

                // Calculate the new red, green, and blue values using the sepia tone formula.
                int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                // Clamp the new red value to be between 0 and 255.
                p.red = Math.min(255, tr);
                
                // Clamp the new green value to be between 0 and 255.
                p.green = Math.min(255, tg);
                
                // Clamp the new blue value to be between 0 and 255.
                p.blue = Math.min(255, tb);

                // Set the modified pixel back into the data array.
                data[row][col] = p;
            }
        }

        // Set the modified pixel data back to the image.
        pi.setData(data);
    }
}
