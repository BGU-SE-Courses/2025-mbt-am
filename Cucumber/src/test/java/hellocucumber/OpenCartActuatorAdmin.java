package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//import org.junit.Assert;


public class OpenCartActuatorAdmin {

    protected WebDriver driver;
    private WebDriverWait wait;


    public OpenCartActuatorAdmin() {
        String driverPath = "Selenium/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCartAdmin() throws InterruptedException {
        // Navigate to the OpenCart website
        driver.get("http://localhost/OpenCartsite/admin_");
        Thread.sleep(1000);
    }

    public void enlargeWindow(){
        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

    }

    public void enterLoginInfoAdmin(String username, String password) {
        // Enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-username']"))).sendKeys(username);
        // Enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        // Wait for a moment
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[1]")));
        loginButton.click();
    }

    public void closeBrowser() throws InterruptedException {
        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }


}
