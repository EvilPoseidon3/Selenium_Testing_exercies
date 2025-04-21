package com.revature;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Level2POM {

    //    private static WebDriver webDriverChrome = WebDriverChrome.startDriverHeadless("C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\Important Resources\\InFormed\\level-2.html");
    String url = "C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\Important Resources\\InFormed\\level-2.html";
    String filePath = "C:\\Users\\Preston\\OneDrive\\Desktop\\Code\\Selenium_Testing_exercies\\src\\main\\resources\\screenshots";
    private WebDriver driver;

    @FindBy(name = "input1")
    private WebElement dropdownelement;
    @FindBy( xpath = "//input[@type='checkbox']")
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
    }

    public static String dateFlipper(String date){
        String[] ar = date.split("-");
        return (ar[1] + ar [2] + ar[0]);
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
        submit.click();


    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-2.png"));
    }


    public static void main(String[] args) {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        try {
            driver = new ChromeDriver(options);

            Level2POM pom = new Level2POM(driver);
            pom.openLevel2();
            pom.assertValues();

            pom.takeScreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (driver != null) driver.quit();


        }
    }
}