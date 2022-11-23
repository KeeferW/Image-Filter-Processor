package view;

import java.io.IOException;

import model.Image;
import model.ImageProcessViewModel;

/**
 * Class that displays user interaction with an image via text user interface.
 */
public class ImageTextView implements ImageView {
  private final ImageProcessViewModel m;
  private final Appendable a;

  /**
   * Constructs a view of an image.
   *
   * @param m - an ImageProcessingViewModel object
   * @throws IllegalArgumentException if the given model is null
   */
  public ImageTextView(ImageProcessViewModel m) throws IllegalArgumentException {
    this(m, System.out);
  }

  /**
   * Constructs a view for a given model and dest.
   *
   * @param m - an ImageProcessingViewModel model
   * @param a - an Appendable object
   * @throws IllegalArgumentException if the given ImageProcessingViewModel or Appendable object
   *         is null
   */
  public ImageTextView(ImageProcessViewModel m, Appendable a)
          throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Model object is null");
    }
    if (a == null) {
      throw new IllegalArgumentException("Appendable object is null");
    }
    this.m = m;
    this.a = a;
  }

  /**
   * Renders a String to a dest.
   *
   * @param s - message String
   * @throws IOException if the program cannot save an image to a destination
   */
  @Override
  public void renderMessage(String s) throws IOException {
    this.a.append(s + "\n");
  }

  /**
   * Renders an image a folder.
   *
   * @param s - saved image names
   * @throws IOException if the program cannot save an image to a destination
   */
  @Override
  public void renderImage(String s) throws IOException {
    Image image = m.getImage(s);
    this.a.append("Image name - " + s + ":" + "\n");
    this.a.append("Maximum Color Value: " + image.getMaximumValue() + "\n");
    this.a.append("Height: " + image.getHeight() + " pixels" + "\n");
    this.a.append("Width: " + image.getWidth() + " pixels" + "\n");
  }
}