/**
 * Filter that adjusts the brightness of the image.
 * Positive values increase brightness, while negative values decrease brightness.
 */
public class BrightnessFilter implements Filter {
    private int brightnessFactor;

    /**
     * Constructs a BrightnessFilter with the specified brightness factor.
     * @param brightnessFactor The amount by which to adjust the brightness.
     *                         Positive values increase brightness, negative values decrease brightness.
     */
    public BrightnessFilter(int brightnessFactor) {
        this.brightnessFactor = brightnessFactor;
    }

    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        int height = pi.getHeight();
        int width = pi.getWidth();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel pixel = data[row][col];
                int red = Math.min(Math.max(pixel.red + brightnessFactor, 0), 255);
                int green = Math.min(Math.max(pixel.green + brightnessFactor, 0), 255);
                int blue = Math.min(Math.max(pixel.blue + brightnessFactor, 0), 255);

                data[row][col] = new Pixel(red, green, blue);
            }
        }

        pi.setData(data);
    }
}
