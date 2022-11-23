import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import controller.ImageTextController;
import model.ImageImpl;
import model.ImageProcessOperations;
import model.ImageUtil;
import view.ImageTextView;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Testing for the Controller.
 */
public class ImageTextControllerTest {
  ImageProcessOperations proc1;
  double[][][] pix;
  ImageImpl im1;

  ImageTextView view;
  Readable read;
  StringBuilder sb;

  private void initData() throws IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[1][2][3];
    pix[0][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    this.sb = new StringBuilder();
    this.view = new ImageTextView(proc1, sb);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoModel() throws IOException {
    this.initData();
    new ImageTextController(null, view, new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoView() throws IOException {
    this.initData();
    new ImageTextController(proc1, null, new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoRead() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, null);
  }

  @Test
  public void testPossibleInputs() throws IOException {
    this.initData();
    new ImageTextController(proc1, view,
            new InputStreamReader(System.in)).displayPossibleInputs();
    assertTrue(sb.toString().contains("red-component image-name"));
  }

  @Test
  public void testRun() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, new StringReader("load first testFirst quit")).run();
    assertEquals(proc1.getImage("testFirst").getHeight(), 2);
  }

  @Test(expected = NoSuchElementException.class)
  public void testBadRun() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, new StringReader("bad")).run();
  }

  @Test
  public void testSetSourcePath() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, new StringReader("set-source pathTest quit")).run();
    assertTrue(sb.toString().contains("Source folder has been set to pathTest"));
  }

  @Test
  public void testAlterImage() throws IOException {
    this.initData();
    double[][][] pix2 = new double[1][2][3];
    pix2[0][0] = new double[]{5, 10, 20};
    ImageImpl im2 = new ImageImpl(pix2, 255);
    proc1.load("first", "first");
    new ImageTextController(proc1, view,
            new StringReader("horizontal-flip first flipTest quit")).run();
    double[] check1 = proc1.getImage("flipTest").getPixel(0, 1);
    double[] check2 = im2.getPixel(0, 0);
    assertArrayEquals(check1, check2, 0.0);
  }

  @Test
  public void testSaveAndLoad() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, new StringReader("load first first quit")).run();
    assertEquals(proc1.getImage("first").getHeight(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSaveAndLoad() throws IOException {
    this.initData();
    new ImageTextController(proc1, view, new StringReader("load fake first quit")).run();
    assertEquals(proc1.getImage("first").getHeight(), 1);
  }
}