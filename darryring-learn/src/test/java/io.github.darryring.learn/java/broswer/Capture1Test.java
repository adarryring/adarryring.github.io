package io.github.darryring.learn.java.broswer;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by xh on 2019-12-21 11:46
 *
 * @explain
 */
class Capture1Test {

    @Test
    void t1(){
        String url = "http://www.360doc.com/content/14/0127/09/11253639_348254060.shtml";
        try {
            // open with local browser
            Desktop.getDesktop().browse(new URL(url).toURI());

            // wait
            Robot robot = new Robot();
            robot.delay(10 * 1000);

            // get screen size
            Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            int w = (int) dimension.getWidth();
            int h = (int) dimension.getHeight();

            // make custom screen size
            robot.keyRelease(KeyEvent.VK_F11);
            robot.delay(2 * 1000);

            // take a capture image
            Image screenCapture = robot.createScreenCapture(new Rectangle(0, 0, w, h));

            // save to local image file
            BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.createGraphics();
            graphics.drawImage(screenCapture, 0, 0, w, h, null);
            ImageIO.write(bufferedImage, "jpg", new File("C:\\Users\\xh\\Desktop/1.jpg"));
        } catch (IOException | URISyntaxException | AWTException e) {
            e.printStackTrace();
        }
    }
}
