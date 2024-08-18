public class NegativeFilter implements Filter {
    /**
     * Applies the negative filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Retrieve the pixel data from the image
        Pixel[][] data = pi.getData();
        int height = pi.getHeight();
        int width = pi.getWidth();

        // Loop through each row of the image
        for (int row = 0; row < height; row++) {
            // Loop through each column in the current row
            for (int col = 0; col < width; col++) {
                // Get the current pixel at (row, col)
                Pixel pixel = data[row][col];

                // Calculate the negative color values by subtracting from 255
                int red = 255 - pixel.red;
                int green = 255 - pixel.green;
                int blue = 255 - pixel.blue;

                // Create a new Pixel with the negative color values and set it in the data array
                data[row][col] = new Pixel(red, green, blue);
            }
        }

        // Update the PixelImage with the new pixel data
        pi.setData(data);
    }
}
