package model;

/**
 * Represents an image model only to be viewed.
 */
public interface ImageProcessViewModel {

  /**
   * Retrieves an image with a given name.
   *
   * @param name - name of an image
   *
   * @return the image
   * @throws IllegalArgumentException if the given name cannot be found
   */
  Image getImage(String name) throws IllegalArgumentException;
}
