package controller;


/**
 * Represents a controller Interface for image processing and user interaction.
 */
public interface ImageProcessController {

  /**
   * Initializes the program, prints a statement and begins accepting input.
   *
   * @throws IllegalStateException if the program cannot read or print
   */
  void run() throws IllegalStateException;
}