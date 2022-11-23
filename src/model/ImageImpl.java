package model;

/**
 * Represents image dimensions in pixels.
 */
public class ImageImpl implements Image {
  private final double[][][] p;
  private final int w;
  private final int h;
  private final int maxValue;

  /**
   * Constructs an image with a given pixel array.
   *
   * @param p - an array of pixels
   * @throws IllegalArgumentException if the array is null
   * @throws IllegalArgumentException if not given 3 color components
   */
  public ImageImpl(double[][][] p, int maxValue) throws IllegalArgumentException {
    if (p == null) {
      throw new IllegalArgumentException("The provided pixels array cannot be null");
    }
    if (p[0][0].length != 3) {
      throw new IllegalArgumentException("Not given 3 colors");
    }
    this.p = p;
    this.w = this.p[0].length;
    this.h = this.p.length;
    this.maxValue = maxValue;
  }

  /**
   * Retrieves a pixel with a given row and column.
   *
   * @param c - column number
   * @param r - row number
   * @return an RGB value of a pixel
   */
  @Override
  public double[] getPixel(int r, int c) {
    return this.p[r][c];
  }

  /**
   * Retrieves the image's max color value.
   */
  @Override
  public int getMaximumValue() {
    return this.maxValue;
  }

  /**
   * Retrieves the width of this image.
   *
   * @return the number of pixels in the width
   */
  @Override
  public int getWidth() {
    return this.w;
  }

  /**
   * Retrieves the height of this image.
   *
   * @return the number of pixels in the height
   */
  @Override
  public int getHeight() {
    return this.h;
  }

}
