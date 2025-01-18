package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//import org.junit.Assert;

public class OpenCartActuatorUser {
    private WebDriver driver;
    private WebDriverWait wait;

    public OpenCartActuatorUser() {
        String driverPath = "Selenium/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCart() throws InterruptedException {
        // Navigate to the OpenCart website
        driver.get("http://localhost/OpenCartsite");
        Thread.sleep(1000);
    }

    public void enlargeWindow(){
        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

    }

    public void scrollToReviews() {
        // Use an XPath to locate the reviews section
        scrollToElement(By.xpath("//main[1]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]")); // Replace with the correct XPath for the reviews section
    }

    public void scrollToElement(By elementLocator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Wait for a moment
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void goToMacBookProduct(){
        driver.findElement(By.xpath("//main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
    }

    public void goToReviews() {
        try {
            WebElement reviewsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//main[1]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reviewsTab);
        } catch (Exception e) {
            System.out.println("Error clicking on the reviews tab: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeAReview(String name, String review, int rating) {
        // Wait until the name input is present and interactable
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-author']")));
        nameElement.sendKeys(name);

        // Wait until the review input is present and interactable
        WebElement reviewElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-text']")));
        reviewElement.sendKeys(review);

        // Wait until the rating input is clickable and click the corresponding rating
        String ratingXPath = "//div[4]/div[1]/input[x]".replace("x", String.valueOf(rating - 1));
        WebElement ratingElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ratingXPath)));
        ratingElement.click();

        // Wait until the "Agree" checkbox or button is clickable and click it
        WebElement continueCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='button-review']")));
        continueCheckbox.click();

        // Optionally, wait for a success message or completion confirmation
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }



    public void closeBrowser() throws InterruptedException {
        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }
}
