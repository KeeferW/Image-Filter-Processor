import org.junit.Test;

import model.ImageImpl;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the image dimensions in pixels.
 */
public class ImageImplTest {


  @Test(expected = IllegalArgumentException.class)
  public void testNullConstruct() {
    new ImageImpl(null, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadColorConstruct() {
    double[][][] pix = new double[5][5][5];
    new ImageImpl(pix, 255);
  }

  @Test
  public void testGoodConstruct() {
    double[][][] pix = new double[5][5][3];
    pix[0][0] = new double[]{1, 1, 1};
    ImageImpl im1 = new ImageImpl(pix, 255);
    assertEquals(im1.getMaximumValue(), 255);

  }

  @Test
  public void testGetPixel() {
    double[][][] pix = new double[5][5][3];
    pix[0][0] = new double[]{1, 1, 1};
    ImageImpl im1 = new ImageImpl(pix, 255);
    assertEquals(im1.getPixel(0, 0).length, 3);
  }

  @Test
  public void testWidth() {
    double[][][] pix = new double[10][20][3];
    ImageImpl im1 = new ImageImpl(pix, 255);
    assertEquals(im1.getWidth(), 20);
  }

  @Test
  public void testHeight() {
    double[][][] pix = new double[10][20][3];
    ImageImpl im1 = new ImageImpl(pix, 255);
    assertEquals(im1.getHeight(), 10);
  }

}