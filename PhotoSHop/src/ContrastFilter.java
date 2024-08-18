/**
 * Filter that adjusts the contrast of the image.
 * Positive values increase contrast, while negative values decrease contrast.
 */
public class ContrastFilter implements Filter {
    private double contrastFactor;

    /**
     * Constructs a ContrastFilter with the specified contrast factor.
     * @param contrastFactor The amount by which to adjust the contrast.
     *                       Positive values increase contrast, negative values decrease contrast.
     */
    public ContrastFilter(double contrastFactor) {
        this.contrastFactor = contrastFactor;
    }

    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        int height = pi.getHeight();
        int width = pi.getWidth();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel pixel = data[row][col];
                int red = adjustContrast(pixel.red);
                int green = adjustContrast(pixel.green);
                int blue = adjustContrast(pixel.blue);

                data[row][col] = new Pixel(red, green, blue);
            }
        }

        pi.setData(data);
    }

    private int adjustContrast(int colorValue) {
        double factor = (259 * (contrastFactor + 255)) / (255 * (259 - contrastFactor));
        int adjustedValue = (int) (factor * (colorValue - 128) + 128);

        // Clamp the value to ensure it's within the valid range (0 to 255)
        return Math.min(Math.max(adjustedValue, 0), 255);
    }
}
