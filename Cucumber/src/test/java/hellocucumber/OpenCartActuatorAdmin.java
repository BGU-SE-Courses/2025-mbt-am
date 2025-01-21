package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class OpenCartActuatorAdmin {
    private WebDriver driver;
    private WebDriverWait wait;

    // Map to store all XPath locators
    private static final Map<String, String> XPATH_LOCATORS = new HashMap<>();

    static {
        XPATH_LOCATORS.put("URL", "http://localhost/opencartsite/admin/");
        XPATH_LOCATORS.put("USERNAME", "//*[@id='input-username']");
        XPATH_LOCATORS.put("PASSWORD", "//*[@id='input-password']");
        XPATH_LOCATORS.put("CONTINUE_BUTTON", "//button[1]");
        XPATH_LOCATORS.put("EXIT_WARNING_BUTTON", "//*[@id=\"modal-security\"]/div/div/div[1]/button");
        XPATH_LOCATORS.put("CATALOG_BUTTON", "//nav[1]/ul[1]/li[2]/a[1]");
        XPATH_LOCATORS.put("PRODUCTS_BUTTON", "//*[@id=\"collapse-1\"]/li[2]/a");
        XPATH_LOCATORS.put("PRODUCT_NAME", "//*[@id='input-name']");
        XPATH_LOCATORS.put("PRODUCT_MODEL", "//*[@id='input-model']");
        XPATH_LOCATORS.put("FILTER_BUTTON", "//*[@id='button-filter']");
        XPATH_LOCATORS.put("EDIT_BUTTON", "//td[7]/div[1]/a[1]");
        XPATH_LOCATORS.put("DATA_TAB_BUTTON", "//form[1]/ul[1]/li[2]/a[1]");
        XPATH_LOCATORS.put("STATUS_BOX", "//fieldset[4]/div[6]/label[1]");
        XPATH_LOCATORS.put("STATUS_TOGGLE", "//*[@id='input-status']");
        XPATH_LOCATORS.put("SAVE_BUTTON", "//div[1]/div[1]/div[1]/button[1]");
        XPATH_LOCATORS.put("SUCCESS_ALERT", "//div[contains(@class, 'alert-success')]");
        XPATH_LOCATORS.put("TOP_OF_PAGE", "//header[1]/div[1]");
    }

    // webDriver: absolute path to webDriver, driverPath: "Selenium/chromedriver.exe"
    public void initSession(String webDriver, String path) {
        System.setProperty(webDriver, path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        try {
            openCartAdmin();
            enlargeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCartAdmin() {
        try {
            driver.get(XPATH_LOCATORS.get("URL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enlargeWindow() {
        driver.manage().window().maximize();
    }

    public void LogInToAdmin(String userName, String password) {
        try {
            WebElement userNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("USERNAME"))));
            userNameElement.sendKeys(userName);

            WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("PASSWORD"))));
            passwordElement.sendKeys(password);

            WebElement continueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("CONTINUE_BUTTON"))));
            continueButtonElement.click();

            WebElement exitWarningElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("EXIT_WARNING_BUTTON"))));
            exitWarningElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToProductsPage() {
        try {
            WebElement catalogTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("CATALOG_BUTTON"))));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", catalogTab);

            WebElement productsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("PRODUCTS_BUTTON"))));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findProductInProducts(String productName, String model) {
        try {
            zoomOut();
            WebElement productNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("PRODUCT_NAME"))));
            productNameElement.sendKeys(productName);

            WebElement productModelElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("PRODUCT_MODEL"))));
            productModelElement.sendKeys(model);

            scrollToElement(By.xpath(XPATH_LOCATORS.get("FILTER_BUTTON")));

            WebElement filterButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("FILTER_BUTTON"))));
            filterButtonElement.click();

            scrollToTopOfPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideTopProduct() {
        try {
            zoomOut();
            WebElement editButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("EDIT_BUTTON"))));
            editButtonElement.click();

            WebElement dataTabButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("DATA_TAB_BUTTON"))));
            dataTabButtonElement.click();

            zoomOut();
            scrollToElement(By.xpath(XPATH_LOCATORS.get("STATUS_BOX")));

            WebElement statusToggleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("STATUS_TOGGLE"))));
            statusToggleElement.click();

            scrollToTopOfPage();

            WebElement saveButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOCATORS.get("SAVE_BUTTON"))));
            saveButtonElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotASuccessMessage() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_LOCATORS.get("SUCCESS_ALERT"))));
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollToTopOfPage() {
        scrollToElement(By.xpath(XPATH_LOCATORS.get("TOP_OF_PAGE")));
    }

    public void scrollToElement(By elementLocator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void zoomOut() {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%';");
    }

    public void closeBrowser() {
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
