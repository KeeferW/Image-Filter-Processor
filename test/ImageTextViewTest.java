import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import model.ImageImpl;
import model.ImageProcessOperations;
import model.ImageUtil;
import view.ImageTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing for the view.
 */
public class ImageTextViewTest {
  ImageProcessOperations proc1;
  double[][][] pix;
  ImageImpl im1;

  private void initData() throws IOException {
    this.proc1 = new ImageProcessOperations();
    this.pix = new double[2][1][3];
    pix[1][0] = new double[]{5, 10, 20};
    this.im1 = new ImageImpl(pix, 255);
    ImageUtil.savePPM(im1, "first");
    proc1.load("first", "testFirst");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoModel() {
    new ImageTextView(null, System.out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoAppend() throws IOException {
    this.initData();
    new ImageTextView(proc1, null);
  }

  @Test
  public void testOutput() throws IOException {
    PrintStream normal = System.out;
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray));
    this.initData();
    new ImageTextView(proc1);
    ByteArrayOutputStream byteArray2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray2));
    ImageUtil.readPPM("first");
    assertEquals(byteArray.toString(), byteArray2.toString());
    System.setOut(normal);
  }

  @Test
  public void testRenderMessage() throws IOException {
    PrintStream normal = System.out;
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray));
    this.initData();
    new ImageTextView(proc1).renderMessage("testing");
    ByteArrayOutputStream byteArray2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray2));
    ImageUtil.readPPM("first");
    assertEquals(byteArray.toString(), byteArray2.toString() + "testing\n");
    System.setOut(normal);
  }

  @Test
  public void testRenderImage() throws IOException {
    PrintStream normal = System.out;
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray));
    this.initData();
    new ImageTextView(proc1).renderImage("testFirst");
    ByteArrayOutputStream byteArray2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArray2));
    ImageUtil.readPPM("first");
    assertTrue(byteArray.toString().contains("Image name - testFirst"));
    System.setOut(normal);
  }
}