package ru.beru;

import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HighlightElement {
    public void highlighter(WebDriver driver, WebElement element) {
        AShot scr = new AShot();
        scr.imageCropper(new IndentCropper().addIndentFilter(new BlurFilter())).takeScreenshot(driver, element);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
        String name = "/Users/yanochka/IdeaProjects/testing/src/test/screenshots/" + dateFormat.format(date) + ".png";
        try {
            Screenshot sc = new AShot().imageCropper(new IndentCropper(2560).addIndentFilter(new BlurFilter()))
                    .takeScreenshot(driver, element);
            BufferedImage bf = sc.getImage();
            ImageIO.write(bf, "PNG", new File(name));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
