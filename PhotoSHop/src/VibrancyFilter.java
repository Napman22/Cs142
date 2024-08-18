public class VibrancyFilter implements Filter {
    /**
     * Applies the vibrancy filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Get the pixel data from the image
        Pixel[][] data = pi.getData();
        int height = pi.getHeight();
        int width = pi.getWidth();

        // Loop through each pixel in the image
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel pixel = data[row][col];
                // Adjust vibrancy for each color channel
                int red = adjustVibrancy(pixel.red);
                int green = adjustVibrancy(pixel.green);
                int blue = adjustVibrancy(pixel.blue);

                // Set the new pixel values
                data[row][col] = new Pixel(red, green, blue);
            }
        }

        // Update the image with the new pixel data
        pi.setData(data);
    }

    /**
     * Adjusts the vibrancy of a color value by scaling it and clamping to the valid range.
     * 
     * @param colorValue The original color value.
     * @return The adjusted color value.
     */
    private int adjustVibrancy(int colorValue) {
        double factor = 1.2; // Vibrancy factor (can be adjusted for more or less vibrancy)
        int adjustedValue = (int) (colorValue * factor);

        // Clamp the value to ensure it's within the valid range (0 to 255)
        return Math.min(Math.max(adjustedValue, 0), 255);
    }
}
