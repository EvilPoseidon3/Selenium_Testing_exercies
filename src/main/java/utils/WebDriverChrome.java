package utils;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverChrome {
    public static WebDriver driver;

    public static void startDriver() {
        if (driver == null){
            try {
                driver = new ChromeDriver();

            } catch (IllegalStateException e) {
                System.out.println("ChromeDriver not found or system property not set: " + e.getMessage());
            } catch (SessionNotCreatedException e) {
                System.out.println("Version mismatch or failed to create a session: " + e.getMessage());
            } catch (WebDriverException e) {
                System.out.println("WebDriver error occurred: " + e.getMessage());
            }
        }
    }

    public static void startDriver(String url) {
        if (driver == null){
            try {
                driver = new ChromeDriver();
                driver.get(url);

            }catch (IllegalStateException e) {
                System.out.println("ChromeDriver not found or system property not set: " + e.getMessage());
            } catch (SessionNotCreatedException e) {
                System.out.println("Version mismatch or failed to create a session: " + e.getMessage());
            } catch (WebDriverException e) {
                System.out.println("WebDriver error occurred: " + e.getMessage());
            }
        }
    }

    public static void startDriverHeadless() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        if (driver == null){
            try {
                driver = new ChromeDriver(options);


            }catch (IllegalStateException e) {
                System.out.println("ChromeDriver not found or system property not set: " + e.getMessage());
            } catch (SessionNotCreatedException e) {
                System.out.println("Version mismatch or failed to create a session: " + e.getMessage());
            } catch (WebDriverException e) {
                System.out.println("WebDriver error occurred: " + e.getMessage());
            }
        }
    }

    public static WebDriver startDriverHeadless(String url) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        if (driver == null){
            try {
                driver = new ChromeDriver(options);
                driver.get(url);


            }catch (IllegalStateException e) {
                System.out.println("ChromeDriver not found or system property not set: " + e.getMessage());
            } catch (SessionNotCreatedException e) {
                System.out.println("Version mismatch or failed to create a session: " + e.getMessage());
            } catch (WebDriverException e) {
                System.out.println("WebDriver error occurred: " + e.getMessage());
            }
        }
        return driver;
    }

    public static void stopDriver(){
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
