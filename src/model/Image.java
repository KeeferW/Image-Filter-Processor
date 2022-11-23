package model;

/**
 * Helps retrieve data about an image.
 */
public interface Image {
  /**
   * Retrieves a pixel with a given row and column.
   *
   * @param r - row number
   * @param c - column number
   * @return an RGB value of a pixel
   */
  double[] getPixel(int r, int c);

  /**
   * Retrieves the image's max color value.
   */
  int getMaximumValue();

  /**
   * Retrieves the width of this image.
   * @return the width of this image in pixels
   */
  int getWidth();

  /**
   * Retrieves the height of this image.
   * @return the height of this image in pixels
   */
  int getHeight();
}
