public class UnsharpMaskingFilter implements Filter {
    /**
     * Applies the unsharp masking filter to the given PixelImage.
     * 
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Define the 3x3 kernel for unsharp masking
        int[][] kernel = {
            { -1, -2, -1 },
            { -2, 28, -2 },
            { -1, -2, -1 }
        };

        // Apply the filter with scaling and adding original pixel values
        applyFilter(pi, kernel, 16, true);
    }

    /**
     * Applies the given kernel to the PixelImage.
     * 
     * @param pi           The PixelImage to be filtered.
     * @param kernel       The convolution kernel.
     * @param scale        The scaling factor for the kernel.
     * @param addOriginal  Whether to add the original pixel values.
     */
    private void applyFilter(PixelImage pi, int[][] kernel, double scale, boolean addOriginal) {
        int kernelSize = kernel.length;
        int kernelRadius = kernelSize / 2;

        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Loop through each row of the image
        for (int row = 0; row < pi.getHeight(); row++) {
            // Loop through each column in the current row
            for (int col = 0; col < pi.getWidth(); col++) {
                double redSum = 0, greenSum = 0, blueSum = 0;

                // Apply the kernel to the surrounding pixels
                for (int i = -kernelRadius; i <= kernelRadius; i++) {
                    for (int j = -kernelRadius; j <= kernelRadius; j++) {
                        // Ensure the indices are within bounds
                        int imageRow = Math.min(Math.max(row + i, 0), pi.getHeight() - 1);
                        int imageCol = Math.min(Math.max(col + j, 0), pi.getWidth() - 1);
                        Pixel pixel = data[imageRow][imageCol];
                        double kernelValue = kernel[i + kernelRadius][j + kernelRadius];

                        // Accumulate the weighted sum of the pixel values
                        redSum += pixel.red * kernelValue;
                        greenSum += pixel.green * kernelValue;
                        blueSum += pixel.blue * kernelValue;
                    }
                }

                // Scale the accumulated sums
                int red = (int) (redSum / scale);
                int green = (int) (greenSum / scale);
                int blue = (int) (blueSum / scale);

                // Optionally add the original pixel values
                if (addOriginal) {
                    red += data[row][col].red;
                    green += data[row][col].green;
                    blue += data[row][col].blue;
                }

                // Clamp the values to be between 0 and 255
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                // Set the new pixel values in the new data array
                newData[row][col] = new Pixel(red, green, blue);
            }
        }

        // Update the PixelImage with the new pixel data
        pi.setData(newData);
    }
}
