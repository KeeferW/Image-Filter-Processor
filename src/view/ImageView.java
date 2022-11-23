package view;

import java.io.IOException;

/**
 * Interface represents a view for an image processor displayed to the user.
 */
public interface ImageView {

  /**
   * Renders a String to a dest.
   *
   * @param s - message String
   * @throws IOException if the program cannot save an image to a destination
   */
  void renderMessage(String s) throws IOException;

  /**
   * Renders an image a folder.
   *
   * @param s - saved image names
   * @throws IOException if the program cannot save an image to a destination
   */
  void renderImage(String s) throws IOException;
}
