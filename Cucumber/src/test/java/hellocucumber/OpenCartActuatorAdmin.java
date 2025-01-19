package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//import org.junit.Assert;

public class OpenCartActuatorAdmin {
    private WebDriver driver;
    private WebDriverWait wait;

    public OpenCartActuatorAdmin() {
        String driverPath = "Selenium/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCartAdmin() throws InterruptedException {
        // Navigate to the OpenCart website
        driver.get("http://localhost/opencartsite/admin/");
        Thread.sleep(1000);
    }

    public void enlargeWindow(){
        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

    }

    public void LogInToAdmin(String userName, String password) throws InterruptedException {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-username']")));
        nameElement.sendKeys(userName);

        // Wait until the review input is present and interactable
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-password']")));
        passwordElement.sendKeys(password);

        // Wait until the "Agree" checkbox or button is clickable and click it
        WebElement continueCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[1]")));
        continueCheckbox.click();

        // Wait until the "Agree" checkbox or button is clickable and click it
        WebElement exitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modal-security\"]/div/div/div[1]/button")));
        exitElement.click();

    }


//    public void scrollToElement(By elementLocator) {
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        // Wait for a moment
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    public void closeBrowser() throws InterruptedException {
        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }
}
