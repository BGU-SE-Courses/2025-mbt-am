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

    // webDriver: "webdriver.chrome.driver", driverPath: "Selenium/chromedriver.exe"
    public void initSession(String webDriver, String path){
        System.setProperty(webDriver, path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        try {
            openCartAdmin();
            enlargeWindow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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


    public void goToProducts(){
        try {
            WebElement catalogTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nav[1]/ul[1]/li[2]/a[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", catalogTab);

            WebElement productsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"collapse-1\"]/li[2]/a")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsTab);


        } catch (Exception e) {
            System.out.println("Error clicking on the reviews tab: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void findMacBookInProducts(String productName, String model) throws InterruptedException {
        // Wait until the name input is present and interactable
        WebElement productNameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-name']")));
        productNameElement.sendKeys(productName);

        // Wait until the review input is present and interactable
        WebElement modelElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-model']")));
        modelElement.sendKeys(model);

        scrollToElement(By.xpath("//*[@id='button-filter']"));

        // Wait until the review input is present and interactable
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='button-filter']")));
        filterElement.click();

        scrollToTopOfPage();
    }


    public void hideTopProduct(){
        // Wait until the review input is present and interactable
        WebElement editElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[7]/div[1]/a[1]")));
        editElement.click();

        // Wait until the review input is present and interactable
        WebElement dataElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[1]/ul[1]/li[2]/a[1]")));
        dataElement.click();

        scrollToElement(By.xpath("//fieldset[4]/div[6]/label[1]"));

        // Wait until the review input is present and interactable
        WebElement statusElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-status']")));
        statusElement.click();

        scrollToTopOfPage();

        // Wait until the review input is present and interactable
        WebElement saveElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div[1]/div[1]/button[1]")));
        saveElement.click();
    }

    public void deleteTopProduct(){
        // Wait until the review input is present and interactable
        WebElement checkBoxElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[1]/input[1]")));
        checkBoxElement.click();

        WebElement deleteElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button[3]")));
        deleteElement.click();

    }


    public void scrollToTopOfPage(){
        scrollToElement(By.xpath("//header[1]/div[1]"));
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


    public void closeBrowser() throws InterruptedException {
        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }
}
