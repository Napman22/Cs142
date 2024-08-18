public class GaussianBlur implements Filter {

    // Override the filter method to apply the Gaussian blur to the given PixelImage
    @Override
    public void filter(PixelImage pi) {
        // Define the 3x3 Gaussian kernel
        double[][] kernel = {
            { 1, 2, 1 },
            { 2, 4, 2 },
            { 1, 2, 1 }
        };

        // Sum of all the kernel values for normalization
        double kernelSum = 16; 
        int kernelSize = kernel.length;
        int kernelRadius = kernelSize / 2;

        // Retrieve the pixel data from the PixelImage
        Pixel[][] data = pi.getData();
        // Initialize an array to store the new pixel data after applying the blur
        Pixel[][] newData = new Pixel[pi.getHeight()][pi.getWidth()];

        // Loop over each pixel in the image
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                // Initialize sums for the red, green, and blue color values
                double redSum = 0, greenSum = 0, blueSum = 0;

                // Apply the kernel to the neighborhood of the current pixel
                for (int i = -kernelRadius; i <= kernelRadius; i++) {
                    for (int j = -kernelRadius; j <= kernelRadius; j++) {
                        // Calculate the row and column indices of the neighboring pixel
                        int imageRow = Math.min(Math.max(row + i, 0), pi.getHeight() - 1);
                        int imageCol = Math.min(Math.max(col + j, 0), pi.getWidth() - 1);

                        // Get the pixel at the calculated position
                        Pixel pixel = data[imageRow][imageCol];
                        // Get the kernel value at the corresponding position
                        double kernelValue = kernel[i + kernelRadius][j + kernelRadius];

                        // Accumulate the weighted color values
                        redSum += pixel.red * kernelValue;
                        greenSum += pixel.green * kernelValue;
                        blueSum += pixel.blue * kernelValue;
                    }
                }

                // Normalize the accumulated sums by dividing by the kernel sum
                int red = (int) Math.min(Math.max(redSum / kernelSum, 0), 255);
                int green = (int) Math.min(Math.max(greenSum / kernelSum, 0), 255);
                int blue = (int) Math.min(Math.max(blueSum / kernelSum, 0), 255);

                // Assign the new color values to the new pixel data array
                newData[row][col] = new Pixel(red, green, blue);
            }
        }

        // Set the new pixel data back to the PixelImage
        pi.setData(newData);
    }
}