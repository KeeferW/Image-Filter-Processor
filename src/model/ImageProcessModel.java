package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents image processing functions.
 */
public interface ImageProcessModel extends ImageProcessViewModel {
  /**
   * Loads an image from a given path and changes the image name to a given name.
   *
   * @param path - image path
   * @param name - name of the image
   * @throws FileNotFoundException if the file cannot be found
   */
  void load(String path, String name) throws FileNotFoundException;

  /**
   * Saves the image with a given name to a given path.
   *
   * @param path - image path
   * @param name - name of the image
   * @throws IllegalArgumentException if an image with the given name cannot be found
   * @throws IOException if image cannot be transmitted
   */
  void save(String path, String name) throws IOException;

  /**
   * Visualizes individual red components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void redComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Visualizes individual green components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void greenComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Visualizes individual blue components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void blueComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Constructs a greyscale image with the maximum value for red, green, and blue.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void valueComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Constructs a greyscale image with the luma-component (0.216r + 0.7512g + 0.07512g)
   * of the image.
   *f
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void lumaComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Constructs a greyscale image by calculating the intensity of every pixel.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void intensityComponent(String name, String destName) throws IllegalArgumentException;

  /**
   * Brightens the image by a given amount.
   *
   * @param num - change of brightness level
   * @param name - name of the image
   * @param destName - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  void brighten(int num, String name, String destName) throws IllegalArgumentException;

  /**
   * Mirrors an image horizontally.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void horizontalFlip(String name, String destName) throws IllegalArgumentException;

  /**
   * Mirrors an image vertically.
   *
   * @param name - name of the image
   * @param destName - name of the destination 
   * @throws IllegalArgumentException if the string cannot be found
   */
  void verticalFlip(String name, String destName) throws IllegalArgumentException;

}
