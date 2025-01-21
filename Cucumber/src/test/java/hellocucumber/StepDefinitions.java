package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class StepDefinitions {
    private static OpenCartActuatorUser actuatorUser;
    String driverPath = "Selenium/chromedriver.exe";
    String webDriver = "C:\\Users\\noash\\OneDrive - post.bgu.ac.il\\Studies\\2025A\\SoftwareQualityEngineering\\projects\\project4\\2025-mbt-am\\Selenium\\chromedriver.exe";


    public void OpenCartInitUser() throws InterruptedException {
        System.out.println("--------------- INITIALIZING OPENCART TEST - OPENING WEBPAGE ---------------");

        actuatorUser = new OpenCartActuatorUser();
        actuatorUser.initSession(webDriver,driverPath);
    }


    @Given("The user is on the product page")
    public void theUserIsOnTheProductPage() throws InterruptedException {
        OpenCartInitUser();
        actuatorUser.goToMacBookProduct();

    }

    @And("The user is on review tab")
    public void theUserIsOnReviewTab() {
        actuatorUser.goToReviews();

    }

    @When("The user is writing review with the name {string} and {string} and {string}")
    public void theUserIsWritingReviewWithTheNameAndAnd(String arg0,String arg1, String arg2) {
        actuatorUser.writeAReview(arg0,arg1,Integer.parseInt(arg2));
    }


    @Then("Message display successfully")
    public void messageDisplaySuccessfully() {
        actuatorUser.gotASuccessMessage();
    }



}
