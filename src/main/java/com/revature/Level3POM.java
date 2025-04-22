package com.revature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Level3POM {

    static String url = "C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\Important Resources\\InFormed\\level-3.html";
    static String filePath = "src/main/resources/screenshots/level-3_" + System.currentTimeMillis() + ".png";


//    static JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//input")
    private List<WebElement> input;
    @FindBy(tagName = "button")
    private WebElement button;
    private static WebDriver driver;

    public Level3POM(WebDriver driver) {
        driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void pomGet(WebDriver driver, String url){
        driver.get(url);

    }






    public void assertValues(WebDriver driver){

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // JavaScript to set all input[type="text"] elements to display: inline
        String script = "document.querySelectorAll('input[type=\"text\"]').forEach(el => el.style.display = 'inline');";

        // Execute the script
        js.executeScript(script);

        for (WebElement webElement: input){
            String cs = "fdsfds";
            webElement.sendKeys(cs);
        }
        button.click();
    }

    public void captureAlertWithRobot(String filePath) {
        try {
            // Wait briefly for alert to appear
            Thread.sleep(1000);


            // Capture full screen
            Robot robot = new Robot();
            Rectangle screenRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Save image
            ImageIO.write(screenFullImage, "png", new File(filePath));

            System.out.println("Screenshot captured using Robot and saved to: " + filePath);

        } catch (AWTException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        WebDriver driver = null;

        try{
            driver = new ChromeDriver();
            Level3POM pom = new Level3POM(driver);
            pom.pomGet(driver, url);
            pom.assertValues(driver);
            pom.captureAlertWithRobot(filePath);

        } finally {
            if (driver != null) driver.quit(); driver.quit();
        }
    }


}
