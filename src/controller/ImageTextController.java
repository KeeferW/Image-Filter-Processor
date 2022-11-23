package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.ImageProcessModel;
import view.ImageView;

/**
 * Represents a controller that lets the user perform operations on an image.
 */
public class ImageTextController implements ImageProcessController, ImageOperations {
  private final ImageProcessModel m;
  private final ImageView v;
  private final Scanner scan;
  private String imagePath = "";

  /**
   * Constructs a controller for a text view to process an image.
   *
   * @param m - model object
   * @param v - view object
   * @param r - readable object
   * @throws IllegalArgumentException if any parameters are null
   */
  public ImageTextController(ImageProcessModel m, ImageView v, Readable r)
          throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (v == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (r == null) {
      throw new IllegalArgumentException("Readable cannot be null");
    }

    this.m = m;
    this.v = v;
    this.scan = new Scanner(r);
    this.scan.useDelimiter(",|\\s+");
  }

  /**
   * Displays the different operations the user can input.
   *
   * @throws IOException if view cannot access this
   */
  @Override
  public void displayPossibleInputs() throws IOException {
    this.v.renderMessage("Operations:\n"
            + "load image-path image-name\n"
            + "save image-path image-name\n"
            + "red-component image-name destination\n"
            + "green-component image-name destination\n"
            + "blue-component image-name destination\n"
            + "value-component image-name destination\n"
            + "luma-component image-name destination\n"
            + "intensity-component image-name destination\n"
            + "brighten num image-name destination\n"
            + "horizontal-flip image-name destination\n"
            + "vertical-flip image-name destination\n"
            + "set-source destination\n"
            + "q");
  }

  /**
   * Runs the model and begins taking inputs.
   *
   * @throws IllegalStateException if the controller cannot properly read or write
   */
  @Override
  public void run() throws IllegalStateException {
    boolean quit = false;
    try {
      this.displayPossibleInputs();
      while (!quit || scan.hasNext()) {
        this.v.renderMessage("Enter Image Operation:");
        try {
          switch (scan.next().toLowerCase()) {
            case "load":
              this.load();
              break;
            case "save":
              this.save();
              break;
            case "red-component":
              this.redComponent();
              break;
            case "green-component":
              this.greenComponent();
              break;
            case "blue-component":
              this.blueComponent();
              break;
            case "value-component":
              this.valueComponent();
              break;
            case "luma-component":
              this.lumaComponent();
              break;
            case "intensity-component":
              this.intensityComponent();
              break;
            case "brighten":
              this.brighten();
              break;
            case "horizontal-flip":
              this.horizontalFlip();
              break;
            case "vertical-flip":
              this.verticalFlip();
              break;
            case "set-source":
              this.setSourcePath();
              break;
            case "quit":
              quit = true;
              break;
            default:
              this.v.renderMessage("The provided operation is not supported");
          }
        } catch (IllegalArgumentException e) {
          this.v.renderMessage("Error: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Inputs or outputs failed." + System.lineSeparator());
    }
  }

  /**
   * Loads an image from a given path and changes the image name to a given name.
   *
   * @throws IOException when cannot transmit to view
   */
  @Override
  public void load() throws IOException {
    String path = this.imagePath + scan.next();
    String name = scan.next();
    try {
      this.m.load(path, name);
    } catch (FileNotFoundException e) {
      this.v.renderMessage("File cannot be retrieved: " + e.getMessage());
      return;
    }
    this.v.renderMessage(path + " has been loaded and named " + name);
  }

  /**
   * Saves the image with a given name to a given path.
   *
   * @throws IOException when cannot transmit to view
   */
  @Override
  public void save() throws IOException {
    String destName = this.imagePath + scan.next();
    String name = scan.next();
    this.m.save(destName, name);
    this.v.renderMessage(name + " has been saved as: " + destName);
  }

  /**
   * Mirrors an image horizontally.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void horizontalFlip() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.horizontalFlip(name, destName);
    this.v.renderMessage(name + " has been saved as a horizontally flipped image named: "
            + destName);
  }

  /**
   * Mirrors an image vertically.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void verticalFlip() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.verticalFlip(name, destName);
    this.v.renderMessage(name + " has been saved as a vertically flipped image named: " + destName);
  }

  /**
   * Visualizes individual red components of an image red value of every pixel.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void redComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.redComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a red component greyscale image named: "
            + destName);
  }

  /**
   * Visualizes individual green components of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void greenComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.greenComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a green component greyscale image named: "
            + destName);
  }

  /**
   * Visualizes individual blue components of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void blueComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.blueComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a blue component greyscale image named: "
            + destName);
  }

  /**
   * Visualizes the value of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void valueComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.valueComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a value component greyscale image named: "
            + destName);
  }

  /**
   * Visualizes the luma of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void lumaComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.lumaComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a luma component greyscale image named: "
            + destName);
  }

  /**
   * Visualizes the intensity of an image.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void intensityComponent() throws IllegalArgumentException, IOException {
    String name = scan.next();
    String destName = scan.next();
    this.m.intensityComponent(name, destName);
    this.v.renderMessage(name + " has been saved as a intensity component greyscale image named: "
            + destName);
  }

  /**
   * Brightens the image by a given amount.
   *
   * @throws IllegalArgumentException if the model cannot call this methods
   * @throws IOException if view cannot access this
   */
  @Override
  public void brighten() throws IllegalArgumentException, IOException {
    int num;
    try {
      num = scan.nextInt();
    } catch (IllegalArgumentException e) {
      this.v.renderMessage("Not an integer");
      return;
    }
    String name = scan.next();
    String destName = scan.next();
    this.m.brighten(num, name, destName);
    this.v.renderMessage(name + " has been saved as a brightened/darkened image by a value of "
            + num
            + " and named: " + destName);
  }

  /**
   * Sets the source path folder for loading and saving images.
   *
   * @throws IOException if view cannot access this
   */
  @Override
  public void setSourcePath() throws IOException {
    String path = scan.next();
    this.imagePath = path;
    this.v.renderMessage("Source folder has been set to " + path);
  }
}