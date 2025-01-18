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

    public void initSessionAsAdmin(String webDriver, String path) {
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/OpenCartFile/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().setPosition(new Point(0, 0));
    }

    public void enterLoginInfoAdmin(String username, String password) {
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-username']"))).sendKeys(username);
        // locate the password input box and enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        // locate Log in button and press
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[1]"))).click();
        wait.until(ExpectedConditions.titleContains("Dashboard"));
    }


}
