import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageImpl;
import model.ImageProcessOperations;
import model.ImageUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testing for the Operations and Util.
 */
public class ImageProcessorPPMTest {
  ImageProcessOperations proc1;
  double[][][] pix;
  ImageImpl im1;

  private void initData() {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{1, 1, 1};
    this.im1 = new ImageImpl(pix, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGetImage() {
    ImageProcessOperations proc1 = new ImageProcessOperations();
    proc1.getImage("fakePic");
  }

  @Test
  public void testLoadAndGet() throws IOException {
    this.initData();

    ImageUtil.savePPM(im1, "first");
    proc1.load("first", "testFirst");
    assertEquals(proc1.getImage("testFirst").getHeight(), im1.getHeight());


  }

  @Test(expected = FileNotFoundException.class)
  public void testBadLoad() throws FileNotFoundException {
    this.initData();
    proc1.load("wrongFile", "test");
  }

  @Test
  public void testSave() throws FileNotFoundException, IOException {
    this.initData();
    ImageUtil.savePPM(im1, "first");
    proc1.load("first", "testFirst");
    proc1.save("second", "testFirst");
    proc1.load("second", "testSecond");
    assertEquals(proc1.getImage("testFirst").getHeight(),
            proc1.getImage("testSecond").getHeight());

  }

  @Test
  public void testRedComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{5, 5, 5};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.redComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);

  }

  @Test
  public void testGreenComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{10, 10, 10};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.greenComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);

  }

  @Test
  public void testBlueComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{20, 20, 20};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.blueComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);

  }

  @Test
  public void testValueComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{20, 20, 20};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.valueComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }

  @Test
  public void testLumaComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{5, 10, 20};
    double lumArr = 0.2126 * pix2[0][0][0] + 0.7152 * pix2[0][0][1] + 0.0722 * pix2[0][0][2];
    pix2[0][0] = new double[]{lumArr, lumArr, lumArr};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.lumaComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }

  @Test
  public void testIntensityComponent() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{1, 2, 3};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{2, 2, 2};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.intensityComponent("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }

  @Test
  public void testBrighten() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][1][3];
    pix[0][0] = new double[]{.5, .2, .10};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{1, 1, 1};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.brighten(255,"testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }

  @Test
  public void testHorizontalFlip() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][2][3];
    pix[0][1] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{5, 10, 20};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.horizontalFlip("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }

  @Test
  public void testVerticalFlip() throws FileNotFoundException, IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[2][1][3];
    pix[1][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    double[][][] pix2 = new double[1][1][3];
    pix2[0][0] = new double[]{5, 10, 20};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "testFirst");
    proc1.verticalFlip("testFirst", "testSecond");
    double[] pixTest1 = proc1.getImage("testSecond").getPixel(0, 0);
    double[] pixTest2 = im2.getPixel(0,0);
    assertArrayEquals(pixTest1, pixTest2, 0.0);
  }



}