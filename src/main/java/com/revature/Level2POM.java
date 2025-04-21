package com.revature;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class Level2POM {

    //    private static WebDriver webDriverChrome = WebDriverChrome.startDriverHeadless("C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\Important Resources\\InFormed\\level-2.html");
    String url = "C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\Important Resources\\InFormed\\level-2.html";
    String filePath = "src/main/resources/screenshots/level-2_" + System.currentTimeMillis() + ".png";
    private WebDriver driver;

    @FindBy(name = "input1")
    private WebElement dropdownelement;
    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkboxs;
    @FindBy(name = "radio")
    private List<WebElement> radiolist;
    @FindBy(name = "dateInput")
    private WebElement dateInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;


    public Level2POM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLevel2() {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static String dateFlipper(String date) {
        String[] ar = date.split("-");
        return (ar[1] + ar[2] + ar[0]);
    }


    public void assertValues() {
        List<WebElement> ddList = driver.findElements(By.xpath("//dl/dd"));

        String dropdown = ddList.get(0).getText();
//        String checkbox = ddList.get(1).getText();
        String radio = ddList.get(2).getText();
        String date = ddList.get(3).getText();
//        String button = ddList.get(4).getText();

        Select dropdown1 = new Select(dropdownelement);
        dropdown1.selectByValue(dropdown);
        for (WebElement webElement : checkboxs) {
            if (!webElement.isSelected()) {
                webElement.click();
            }
        }
        for (WebElement webElement : radiolist) {
            if (Objects.equals(webElement.getDomProperty("value"), radio)) {
                webElement.click();
            }
        }
        dateInput.sendKeys(dateFlipper(date));
        try {
            Thread.sleep(1); // Wait for 2 seconds
        } catch (InterruptedException e) {
            // Handle the exception, e.g., log it or take appropriate action
            e.printStackTrace();
        }
        submit.click();


    }


    public void captureScreenshotWithAlert(WebDriver driver, String filePath) {
        try {
            // Wait a moment if needed for alert to appear
            Thread.sleep(1000);

            // Use try-catch around the alert just to be safe
            Alert alert = driver.switchTo().alert();

            // Optional: log the alert text
            System.out.println("Alert text: " + alert.getText());

            // Don't interact with alert yet — take screenshot first
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(filePath));

            // Now handle the alert
            alert.accept(); // or alert.dismiss()

            System.out.println("Screenshot saved and alert accepted.");

        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        try {
            driver = new ChromeDriver();


            Level2POM pom = new Level2POM(driver);
            pom.openLevel2();

            pom.assertValues();

            pom.captureAlertWithRobot(pom.filePath);
        } finally {
            if (driver != null) driver.quit();


        }
    }
}