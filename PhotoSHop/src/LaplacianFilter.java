public class LaplacianFilter implements Filter {
    /**
     * Applies the Laplacian filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Define the Laplacian kernel for edge detection
        int[][] kernel = {
            { -1, -1, -1 },
            { -1, 8, -1 },
            { -1, -1, -1 }
        };

        // Apply the filter using the kernel, scale factor of 1, and without adding the original pixel value
        applyFilter(pi, kernel, 1, false);
    }

    /**
     * Helper method to apply a given kernel filter to the PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     * @param kernel The kernel to be applied.
     * @param scale The scale factor to normalize the kernel's effect.
     * @param addOriginal Whether to add the original pixel value to the result (unused in this context).
     */
    private void applyFilter(PixelImage pi, int[][] kernel, double scale, boolean addOriginal) {
        int kernelSize = kernel.length;
        int kernelRadius = kernelSize / 2;

        // Get the pixel data from the image
        Pixel[][] data = pi.getData();
        // Create a new array to store the filtered image data
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Loop through each row of the image
        for (int row = 0; row < pi.getHeight(); row++) {
            // Loop through each column in the current row
            for (int col = 0; col < pi.getWidth(); col++) {
                double redSum = 0, greenSum = 0, blueSum = 0;

                // Apply the kernel to the neighborhood of the current pixel
                for (int i = -kernelRadius; i <= kernelRadius; i++) {
                    for (int j = -kernelRadius; j <= kernelRadius; j++) {
                        // Calculate the position of the neighboring pixel
                        int imageRow = Math.min(Math.max(row + i, 0), pi.getHeight() - 1);
                        int imageCol = Math.min(Math.max(col + j, 0), pi.getWidth() - 1);
                        Pixel pixel = data[imageRow][imageCol];
                        double kernelValue = kernel[i + kernelRadius][j + kernelRadius];

                        // Sum the weighted pixel values
                        redSum += pixel.red * kernelValue;
                        greenSum += pixel.green * kernelValue;
                        blueSum += pixel.blue * kernelValue;
                    }
                }

                // Normalize the summed values by the scale factor
                int red = (int) (redSum / scale);
                int green = (int) (greenSum / scale);
                int blue = (int) (blueSum / scale);

                // Clamp the values to be between 0 and 255
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                // Create a new Pixel with the computed color values and store it in the new array
                newData[row][col] = new Pixel(red, green, blue);
            }
        }

        // Set the modified pixel data back to the image
        pi.setData(newData);
    }
}
