package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class StepDefinitions {
    private static OpenCartActuatorUser actuatorUser;
    String driverPath = "Selenium/chromedriver.exe";
    String webDriver = "webdriver.chrome.driver";

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
    public void theUserIsWritingReviewWithTheNameAndAnd(String arg0, String arg1, String arg2) {
        actuatorUser.writeAReview(arg0,arg1,Integer.parseInt(arg2));
    }


    @Then("Message display successfully")
    public void messageDisplaySuccessfully() {
        actuatorUser.gotASuccessMessage();
    }






}
