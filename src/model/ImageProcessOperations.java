package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents processing operations for a PPM image.
 */
public class ImageProcessOperations implements ImageProcessModel {
  private final Map<String, Image> imageMap;

  public ImageProcessOperations() {
    this.imageMap = new HashMap<String, Image>();
  }

  /**
   * Constructs a new image with the given operation applied to the original image.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @param op - the operation to use to create a new image
   * @throws IllegalArgumentException if the image cannot be found
   */
  private void newAppliedImage(String name, String d, Function<Image, Integer, Integer,
          double[]> op)
          throws IllegalArgumentException {
    Image init = this.getImage(name);
    int h = init.getHeight();
    int w = init.getWidth();
    double[][][] p = new double[h][w][3];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        p[i][j] = op.apply(init, i, j);
      }
    }
    this.imageMap.put(d, new ImageImpl(p, init.getMaximumValue()));
  }

  /**
   * Retrieves an image with a given name.
   *
   * @param name - name of an image
   * @throws IllegalArgumentException if the given name cannot be found
   *
   * @return the image
   */
  @Override
  public Image getImage(String name) throws IllegalArgumentException {
    if (this.imageMap.get(name) == null) {
      throw new IllegalArgumentException(name + " does not exist");
    }
    return this.imageMap.get(name);
  }

  /**
   * Loads an image from a given path and changes the image name to a given name.
   *
   * @param imagePath - name of the image path
   * @param name - name of the image
   * @throws FileNotFoundException if the file cannot be found
   */
  @Override
  public void load(String imagePath, String name) throws FileNotFoundException {
    Image i;
    try {
      i = ImageUtil.readPPM(imagePath);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(imagePath + " does not exist");
    }
    this.imageMap.put(name, i);
  }

  /**
   * Saves the image with a given name to a given path.
   *
   * @param imagePath - name of the image path
   * @param name - name of the image
   * @throws IllegalArgumentException if an image with the given name cannot be found
   * @throws IOException if image cannot be transmitted
   */
  @Override
  public void save(String imagePath, String name) throws IllegalArgumentException, IOException {
    ImageUtil.savePPM(this.getImage(name), imagePath);
  }

  /**
   * Visualizes individual red components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void redComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> r = (k, i, j) -> {
      double[] redArr = k.getPixel(i, j);
      return new double[] {
              redArr[0], redArr[0], redArr[0]
      };
    };
    this.newAppliedImage(name, d, r);
  }

  /**
   * Visualizes individual green components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void greenComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> g = (k, i, j) -> {
      double[] greenArr = k.getPixel(i, j);
      return new double[] {
              greenArr[1], greenArr[1], greenArr[1]
      };
    };
    this.newAppliedImage(name, d, g);
  }

  /**
   * Visualizes individual blue components of an image red value of every pixel.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void blueComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> b = (k, i, j) -> {
      double[] rgb = k.getPixel(i, j);
      return new double[] {
              rgb[2], rgb[2], rgb[2]
      } ;
    };
    this.newAppliedImage(name, d, b);
  }

  /**
   * Constructs a greyscale image with the maximum value for red, green, and blue.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void valueComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> maxVal = (k, i, j) -> {
      double[] valArr = k.getPixel(i, j);
      return new double[] {
              Math.max(Math.max(valArr[0], valArr[1]), valArr[2]),
              Math.max(Math.max(valArr[0], valArr[1]), valArr[2]),
              Math.max(Math.max(valArr[0], valArr[1]), valArr[2])
      };
    };
    this.newAppliedImage(name, d, maxVal);
  }

  /**
   * Constructs a greyscale image with the luma-component (0.216r + 0.7512g + 0.07512g)
   * of the image.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void lumaComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> l = (k, i, j) -> {
      double[] lum = k.getPixel(i, j);
      double lumArr = 0.2126 * lum[0] + 0.7152 * lum[1] + 0.0722 * lum[2];
      return new double[] {
        lumArr, lumArr, lumArr
      };
    };
    this.newAppliedImage(name, d, l);
  }

  /**
   * Constructs a greyscale image by calculating the intensity of every pixel.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void intensityComponent(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> intensity = (k, i, j) -> {
      double[] intArr = k.getPixel(i, j);
      return new double[] {
        ((intArr[0] + intArr[1] + intArr[2]) / 3),
        ((intArr[0] + intArr[1] + intArr[2]) / 3),
        ((intArr[0] + intArr[1] + intArr[2]) / 3)
      };
    };
    this.newAppliedImage(name, d, intensity);
  }

  /**
   * Brightens the image by a given amount.
   *
   * @param num - change of brightness level
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void brighten(int num, String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> brighten = (k, i, j) -> {
      double[] briArr = k.getPixel(i, j);
      return new double[] {
              Math.max(0, Math.min(1, briArr[0] + num / 255.0)),
              Math.max(0, Math.min(1, briArr[1] + num / 255.0)),
              Math.max(0, Math.min(1, briArr[2] + num / 255.0))
      };
    };
    this.newAppliedImage(name, d, brighten);
  }

  /**
   * Mirrors an image horizontally.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void horizontalFlip(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> horArr = (k, i, j) -> k.getPixel(i, k.getWidth()
            - j - 1);
    this.newAppliedImage(name, d, horArr);
  }

  /**
   * Mirrors an image vertically.
   *
   * @param name - name of the image
   * @param d - name of the destination
   * @throws IllegalArgumentException if the string cannot be found
   */
  @Override
  public void verticalFlip(String name, String d) throws IllegalArgumentException {
    Function<Image, Integer, Integer, double[]> vertical = (k, i, j) -> k.getPixel(k.getHeight()
            - i - 1, j);
    this.newAppliedImage(name, d, vertical);
  }
}
