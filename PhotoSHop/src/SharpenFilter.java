/**
 * Filter that applies a sharpening effect to the image.
 * This filter enhances edges and details in the image.
 */
public class SharpenFilter implements Filter {
    /**
     * Applies the sharpening filter to the given PixelImage.
     * 
     * @param pi The PixelImage to which the filter is applied.
     */
    @Override
    public void filter(PixelImage pi) {
        // Define the sharpening kernel
        int[][] kernel = {
            { -1, -1, -1 },
            { -1, 9, -1 },
            { -1, -1, -1 }
        };

        // Apply the filter using the defined kernel
        applyFilter(pi, kernel);
    }

    /**
     * Applies the specified filter kernel to the given PixelImage.
     * 
     * @param pi     The PixelImage to which the filter is applied.
     * @param kernel The filter kernel.
     */
    private void applyFilter(PixelImage pi, int[][] kernel) {
        int kernelSize = kernel.length;
        int kernelRadius = kernelSize / 2;

        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Iterate over each pixel in the image
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                double redSum = 0, greenSum = 0, blueSum = 0;

                // Apply the kernel to the neighborhood of the current pixel
                for (int i = -kernelRadius; i <= kernelRadius; i++) {
                    for (int j = -kernelRadius; j <= kernelRadius; j++) {
                        // Ensure the pixel coordinates are within bounds
                        int imageRow = Math.min(Math.max(row + i, 0), pi.getHeight() - 1);
                        int imageCol = Math.min(Math.max(col + j, 0), pi.getWidth() - 1);
                        Pixel pixel = data[imageRow][imageCol];
                        double kernelValue = kernel[i + kernelRadius][j + kernelRadius];

                        // Convolve the kernel with the pixel values
                        redSum += pixel.red * kernelValue;
                        greenSum += pixel.green * kernelValue;
                        blueSum += pixel.blue * kernelValue;
                    }
                }

                // Clamp the resulting color values to ensure they are within the valid range
                int red = (int) Math.min(Math.max(redSum, 0), 255);
                int green = (int) Math.min(Math.max(greenSum, 0), 255);
                int blue = (int) Math.min(Math.max(blueSum, 0), 255);

                // Update the pixel in the new image data with the sharpened color values
                newData[row][col] = new Pixel(red, green, blue);
            }
        }

        // Update the PixelImage data with the sharpened image
        pi.setData(newData);
    }
}
