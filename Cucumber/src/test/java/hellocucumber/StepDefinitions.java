package hellocucumber;

import io.cucumber.java.en.*;

public class StepDefinitions {
    private static OpenCartActuatorUser actuatorUser;
    private static OpenCartActuatorAdmin actuatorAdmin;
    String driverPath = "Selenium/chromedriver.exe";
    String webDriver = "C:\\Users\\noash\\OneDrive - post.bgu.ac.il\\Studies\\2025A\\SoftwareQualityEngineering\\projects\\project4\\2025-mbt-am\\Selenium\\chromedriver.exe";


    // USER

    public void OpenCartInitUser() throws InterruptedException {
        System.out.println("--------------- INITIALIZING OPENCART TEST - OPENING WEBPAGE ---------------");

        actuatorUser = new OpenCartActuatorUser();
        actuatorUser.initSession(webDriver,driverPath);
    }


    @Given("The user is on the product page")
    public void theUserIsOnTheProductPage() throws InterruptedException {
        OpenCartInitUser();
        actuatorUser.goToFirstProductInPage();

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
    public void messageDisplaySuccessfully() throws InterruptedException {
        actuatorUser.gotASuccessMessage();
    }


    // ADMIN


    public void OpenCartInitAdmin() throws InterruptedException {
        System.out.println("--------------- INITIALIZING OPENCART ADMIN TEST - OPENING WEBPAGE ---------------");

        actuatorAdmin = new OpenCartActuatorAdmin();
        actuatorAdmin.initSession(webDriver,driverPath);
    }


    @Given("The admin is logged in with {string} and {string}")
    public void theAdminIsLoggedInWithAnd(String arg0, String arg1) throws InterruptedException {
        OpenCartInitAdmin();
        actuatorAdmin.LogInToAdmin(arg0, arg1);
    }

    @And("The admin on the Products Page")
    public void theAdminOnTheProductsPage() {
        actuatorAdmin.goToProductsPage();
    }

    @And("the admin selected specific product")
    public void theAdminSelectedSpecificProduct() throws InterruptedException {
        actuatorAdmin.findProductInProducts("MacBook", "Product 16");
    }

    @When("The admin deletes the product")
    public void theAdminDeletesTheProduct() {
        //TODO: change to delete
        actuatorAdmin.hideTopProduct();
    }

    @Then("check product is deleted from catalog")
    public void checkProductIsDeletedFromCatalog() throws InterruptedException {
        //TODO: check product is deleted from catalog
        actuatorAdmin.gotASuccessMessage();
    }
}
