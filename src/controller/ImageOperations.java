package controller;

import java.io.IOException;

/**
 * Contains methods for image processing.
 */
public interface ImageOperations {
  /**
  * Prints all possible inputs.
  *
  * @throws IOException when cannot transmit to view
  */
  void displayPossibleInputs() throws IOException;

  /**
   * Loads an image from a given path and changes the image name to a given name.
   *
   * @throws IOException when cannot transmit to view
   */
  void load() throws IOException;

  /**
   * Saves the image with a given name to a given path.
   *
   * @throws IOException when cannot transmit to view
   */
  void save() throws IOException;


  /**
   * Mirrors an image horizontally.
   *
   * @throws IllegalArgumentException if the program cannot refer to this
   * @throws IOException              when cannot transmit to view
   */
  void horizontalFlip() throws IllegalArgumentException, IOException;

  /**
   * Mirrors an image vertically.
   *
   * @throws IllegalArgumentException if the program cannot refer to this
   * @throws IOException              when cannot transmit to view
   */
  void verticalFlip() throws IllegalArgumentException, IOException;

  /**
   * Visualizes individual red components of an image red value of every pixel.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void redComponent() throws IllegalArgumentException, IOException;

  /**
   * Visualizes individual green components of an image red value of every pixel.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void greenComponent() throws IllegalArgumentException, IOException;

  /**
   * Visualizes individual blue components of an image red value of every pixel.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void blueComponent() throws IllegalArgumentException, IOException;

  /**
   * Visualizes the value of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void valueComponent() throws IllegalArgumentException, IOException;

  /**
   * Visualizes the luma of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void lumaComponent() throws IllegalArgumentException, IOException;

  /**
   * Visualizes the intensity of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void intensityComponent() throws IllegalArgumentException, IOException;

  /**
   * Brightens the image by a given amount.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException              if view cannot access this
   */
  void brighten() throws IllegalArgumentException, IOException;

  /**
   * Sets the source path folder for loading and saving images.
   *
   * @throws IOException if view cannot access this
   */
  void setSourcePath() throws IOException;
}
