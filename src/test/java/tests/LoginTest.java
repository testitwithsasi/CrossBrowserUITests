package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverFactory;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUpDriver(String browser){
        driver = DriverFactory.getDriver(browser);
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test(priority = 1)
    public void verfiyPageTitle(){
        String expectedTitle = "Test Login | Practice Test Automation";
        String title = driver.getTitle();
        System.out.println("Page Title is: " + title);
        Assert.assertEquals(title, expectedTitle, "Page title is not matching");
    }

    @Test(priority = 2)
    public void verifyLoginPageHeader(){
        WebElement headerElement = driver.findElement(By.tagName("h2"));
        String header = headerElement.getText();
        System.out.println("Login Page Header is: " + header);
        String expectedHeader = "Test login";
        Assert.assertEquals(header, expectedHeader, "Login Page Header is mismatching");
    }

    @Test(priority = 3)
    public void verifyLoginPage(){

        WebElement userNameEl = driver.findElement(By.id("username"));
        userNameEl.sendKeys("student");

        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("Password123");

        driver.findElement(By.id("submit")).click();

        // After login, verify URL contains "Logged in successfully"
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current Url after login is: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("logged-in-successfully"), "Login failed or incorrect URL");

        // Verify logout button is displayed
        WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Log out']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible, login might have failed");
    }

    @AfterMethod
    public  void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
