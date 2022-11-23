package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import controller.ImageProcessController;
import controller.ImageTextController;
import view.ImageTextView;
import view.ImageView;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) throws FileNotFoundException {
    Scanner sc;

    sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("File should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    double[][][] pixArr = new double[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixArr[i][j] = new double[]{
          r / 255., g / 255., b / 255.0
        };
      }
    }
    return new ImageImpl(pixArr, maxValue);
  }

  /**
   * Saves a given image as a PPM image as the given name.
   *
   * @param i - an image
   * @param filename - the file name
   * @throws IOException if the program cannot read/write the file
   */
  public static void savePPM(Image i, String filename) throws IOException {
    StringBuilder ppm = new StringBuilder();
    ppm.append("P3" + "\n" + i.getWidth() + " "
            + i.getHeight() + "\n" + i.getMaximumValue() + "\n");
    for (int row = 0; row < i.getHeight(); row++) {
      for (int col = 0; col < i.getWidth(); col++) {
        double[] pixelArr = i.getPixel(row, col);
        ppm.append((int) (pixelArr[0] * 255)).append(System.lineSeparator());
        ppm.append((int) (pixelArr[1] * 255)).append(System.lineSeparator());
        ppm.append((int) (pixelArr[2] * 255)).append(System.lineSeparator());
      }
    }
    Files.writeString(Paths.get(filename), ppm.toString(), StandardCharsets.UTF_8);
  }

  /**
   * Starts the ImageProcessingController.
   *
   * @param args - command arguments
   */
  public static void main(String[] args) {
    Readable readable;
    if (args.length > 0) {
      StringBuilder s = new StringBuilder();

      if (!(args[args.length - 5].equals("-save"))) {
        throw new IllegalArgumentException("End by saving with '-save file-name'");
      }

      if (!(args[0].equals("-load"))) {
        throw new IllegalArgumentException("Begin by loading file with '-load file-name'");
      }

      for (int i = 2; i < args.length - 2; i++) {
        if (args[i].equalsIgnoreCase("-p")) {
          i++;
          String imageProcess = args[i];
          s.append("Process: ").append(imageProcess + "\n");
        }
      }

      readable = new StringReader(s.toString());
      System.out.print(s + "\n");
    } else {
      readable = new InputStreamReader(System.in);
    }
    ImageProcessModel model = new ImageProcessOperations();
    ImageView view = new ImageTextView(model);
    ImageProcessController c = new ImageTextController(model, view, readable);
    c.run();
  }
}


