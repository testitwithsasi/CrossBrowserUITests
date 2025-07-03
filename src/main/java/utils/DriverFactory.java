package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver(String browser){
        WebDriver driver = null;

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
//            System.out.println("Browser Version: " + ((HasCapabilities) driver).getCapabilities().getBrowserVersion());
        }
        else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            System.out.println("Unsupported browser. Defaulting to Chrome.");
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
        }

        driver.manage().window().maximize();
        return driver;
    }
}
