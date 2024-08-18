
 /**
 * Short Report:
 * * - EdgyFilter: This filter applies an edge detection algorithm to the image by emphasizing pixel differences using a specific kernel.
 * - GaussianBlurFilter: This filter applies a Gaussian blur effect to the image, creating a smooth and softened appearance.
 * - UnsharpMaskingFilter: This filter enhances edges in the image by subtracting a blurred version of the image from the original.
 * - LaplacianFilter: This filter detects edges and highlights them by subtracting the neighboring pixel values from 8 times the center one.
 * - NegativeFilter: This filter transforms the image into its negative version by subtracting each color channel from 255.
 *
 * Additional Filters:
 * - SepiaFilter: This filter applies a sepia tone effect to the image, giving it a warm, vintage appearance.
 * - VibrancyFilter: This filter increases the vibrancy of colors in the image, making them more vivid and intense.
 * - SharpenFilter: Enhances the edges and details in the image by emphasizing the contrast between pixels.
 * 
 * 		- Kernal explanation: The central value (9) represents the weight of the central pixel, which is significantly higher compared to the surrounding pixels.
 *		 The surrounding values (-1) represent the weight of the neighboring pixels. By subtracting these values, the filter emphasizes the differences between the central pixel and its neighbors.
 *		 The sum of the kernel values is 0, ensuring that the filter does not cause overall brightness changes in the image.
 *
 * - ContrastFilter: Adjusts the contrast of the image to make it more vivid or muted.
 * - BrightnessFilter: Modifies the brightness of the image by adding or subtracting a constant value from each pixel.
 * 
 * What Works:
 * All filters (EdgyFilter, GaussianBlurFilter, UnsharpMaskingFilter, LaplacianFilter, NegativeFilter, SepiaFilter, BrightnessFilter,
 * ContrastFilter and VibrancyFilter) work as intended, applying their respective effects to the input image.
 *
 * 
 * Surprises or Problems Encountered:
 * - Dealing with pixels at the edge of the image when applying convolutional filters can be tricky. 
 *	 Depending on the approach used, these pixels may need special handling to prevent artifacts or incorrect results.
 * - The DharpenFilter ended up having the same effect as the EdgyFilter i wanted it to have less artifacting when the filter is applied 
 * 	 couldnt figure out how in time.
 */

/**
 * A class to configure the SnapShop application
 * 
 * @author (Tucker Anderson-Sanford)
 * @version (06.09.24)
 */
public class SnapShopConfiguration {
	/**
	 * Method to configure the SnapShop. Call methods like addFilter and
	 * setDefaultFilename here.
	 * 
	 * @param theShop
	 *            A pointer to the application
	 */
	public static void configure(SnapShop theShop) {

		theShop.setDefaultFilename("billg.jpg");
		theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		theShop.addFilter(new FlipVerticallFilter(), "Flip Vertical");

		// add your other filters below
		theShop.addFilter(new NegativeFilter(), "Negative");
		theShop.addFilter(new VibrancyFilter(), "Vibrance");
		theShop.addFilter(new GaussianBlur(), "Gaussian Blur");
		theShop.addFilter(new UnsharpMaskingFilter(), "Unsharp Masking");
		theShop.addFilter(new LaplacianFilter(), "Laplacian");
		theShop.addFilter(new EdgyFilter(), "Edgy");
		theShop.addFilter(new SepiaFilter(), "Sepia");
		//BrightnessFilter(Int that updates Brightness)
		theShop.addFilter(new BrightnessFilter(5), "Brightness");
		//ContrastFilter(Int that updates Contrast)
		theShop.addFilter(new ContrastFilter(5), "Contrast");
		theShop.addFilter(new SharpenFilter(), "Sharpen");

		


	}
}