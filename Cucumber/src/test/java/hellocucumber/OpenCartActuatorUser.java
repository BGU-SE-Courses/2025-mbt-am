package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class OpenCartActuatorUser {
    private WebDriver driver;
    private WebDriverWait wait;

    // Map to store all XPath locators
    private static final Map<String, String> XPATH_LOCATORS = new HashMap<>();

    static {
        XPATH_LOCATORS.put("URL", "http://localhost/opencartsite");
        XPATH_LOCATORS.put("REVIEWS_TAB", "//div[2]/div[1]/p[1]/a[1]");//main[1]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]
        XPATH_LOCATORS.put("FIRST_PRODUCT_IN_PAGE", "//main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]");
        XPATH_LOCATORS.put("FULL_NAME", "//*[@id='input-author']");
        XPATH_LOCATORS.put("REVIEW_TEXT_BOX", "//*[@id='input-text']");
        XPATH_LOCATORS.put("RATING_SCALE", "//div[4]/div[1]/input[x]");
        XPATH_LOCATORS.put("CONTINUE_BUTTON", "//*[@id='button-review']");
        XPATH_LOCATORS.put("SUCCESS_ALERT", "//div[contains(@class, 'alert-success')]");
    }

    // webDriver: absolute path to webDriver, driverPath: "Selenium/chromedriver.exe"
    public void initSession(String webDriver, String path) {
        System.setProperty(webDriver, path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        openCart();
        enlargeWindow();
    }

    public void openCart() {
        // Navigate to the OpenCart website using the URL from the map
        driver.get(XPATH_LOCATORS.get("URL"));
    }

//    public void scrollToReviews() {
//        // Use an XPath from the map to locate the reviews section
//        scrollToElement(By.xpath(XPATH_LOCATORS.get("REVIEWS_TAB")));
//    }

//    public void scrollToElement(By elementLocator) {
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void goToFirstProductInPage() {
        driver.findElement(By.xpath(XPATH_LOCATORS.get("FIRST_PRODUCT_IN_PAGE"))).click();
    }

    public void goToReviews() {
        try {
            zoomOut();
            WebElement reviewsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("REVIEWS_TAB"))));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reviewsTab);
        } catch (Exception e) {
            System.out.println("Error clicking on the reviews tab: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeAReview(String fullName, String reviewText, int rating) {
        zoomOut();
        WebElement fullNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("FULL_NAME"))));
        fullNameElement.sendKeys(fullName);

        WebElement reviewTextBoxElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("REVIEW_TEXT_BOX"))));
        reviewTextBoxElement.sendKeys(reviewText);

        String ratingXPath = XPATH_LOCATORS.get("RATING_SCALE").replace("x", String.valueOf(rating - 1));
        WebElement ratingScaleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ratingXPath)));
        ratingScaleElement.click();

        WebElement continueCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("CONTINUE_BUTTON"))));
        continueCheckbox.click();
    }

    public void gotASuccessMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_LOCATORS.get("SUCCESS_ALERT"))));
        closeBrowser();
    }

    public void zoomOut() {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%';");
    }

    public void enlargeWindow() {
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
