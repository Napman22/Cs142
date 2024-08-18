public class EdgyFilter implements Filter {
    /**
     * Applies the edgy filter to the given PixelImage using a specific kernel.
     *
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Define the 3x3 kernel for the edgy filter
        int[][] kernel = {
            { -1, -1, -1 },
            { -1,  9, -1 },
            { -1, -1, -1 }
        };

        // Apply the filter with the given kernel, scale, and addOriginal flag
        applyFilter(pi, kernel, 1, true);
    }

    /**
     * Helper method to apply a filter to the given PixelImage using a specified kernel.
     *
     * @param pi           The PixelImage to be filtered.
     * @param kernel       The kernel to use for the filter.
     * @param scale        The scale factor for the kernel values.
     * @param addOriginal  Whether to add the original pixel value to the filtered result.
     */
    private void applyFilter(PixelImage pi, int[][] kernel, double scale, boolean addOriginal) {
        int kernelSize = kernel.length;
        int kernelRadius = kernelSize / 2;

        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Loop through each pixel in the image
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                double redSum = 0, greenSum = 0, blueSum = 0;

                // Loop through each value in the kernel
                for (int i = -kernelRadius; i <= kernelRadius; i++) {
                    for (int j = -kernelRadius; j <= kernelRadius; j++) {
                        // Ensure the pixel index is within the image bounds
                        int imageRow = Math.min(Math.max(row + i, 0), pi.getHeight() - 1);
                        int imageCol = Math.min(Math.max(col + j, 0), pi.getWidth() - 1);
                        Pixel pixel = data[imageRow][imageCol];
                        double kernelValue = kernel[i + kernelRadius][j + kernelRadius];

                        // Apply the kernel value to the corresponding pixel color channels
                        redSum += pixel.red * kernelValue;
                        greenSum += pixel.green * kernelValue;
                        blueSum += pixel.blue * kernelValue;
                    }
                }

                // Scale the resulting color values
                int red = (int) (redSum / scale);
                int green = (int) (greenSum / scale);
                int blue = (int) (blueSum / scale);

                // Add the original pixel values if specified
                if (addOriginal) {
                    red += data[row][col].red;
                    green += data[row][col].green;
                    blue += data[row][col].blue;
                }

                // Clamp the values to ensure they're within the valid range (0 to 255)
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                // Set the new pixel values in the newData array
                newData[row][col] = new Pixel(red, green, blue);
            }
        }

        // Update the image with the new pixel data
        pi.setData(newData);
    }
}

