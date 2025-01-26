package hellocucumber;

import io.cucumber.java.en.*;

public class StepDefinitions {
    private static OpenCartActuatorUser actuatorUser;
    private static OpenCartActuatorAdmin actuatorAdmin;
    String driverPath = "Selenium/chromedriver.exe";
    String webDriver = "C:\\Users\\noash\\OneDrive - post.bgu.ac.il\\Studies\\2025A\\SoftwareQualityEngineering\\projects\\project4\\2025-mbt-am\\Selenium\\chromedriver.exe";
    String productName = "MacBook";
    String productModel = "product 16";

    // USER

    @Given("The user is on the product page")
    public void theUserIsOnTheProductPage(){
        OpenCartInitUser();
        actuatorUser.goToFirstProductInPage();
    }


    public void OpenCartInitUser(){
        System.out.println("--------------- INITIALIZING OPENCART TEST - OPENING WEBPAGE ---------------");

        actuatorUser = new OpenCartActuatorUser();
        actuatorUser.initSession(webDriver,driverPath);
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
    public void messageDisplaySuccessfully(){
        actuatorUser.gotASuccessMessage();
    }


    // ADMIN

    @Given("The admin is logged in with {string} and {string}")
    public void theAdminIsLoggedInWithAnd(String arg0, String arg1){
        OpenCartInitAdmin();
        actuatorAdmin.LogInToAdmin(arg0, arg1);
    }


    public void OpenCartInitAdmin(){
        System.out.println("--------------- INITIALIZING OPENCART ADMIN TEST - OPENING WEBPAGE ---------------");

        actuatorAdmin = new OpenCartActuatorAdmin();
        actuatorAdmin.initSession(webDriver,driverPath);
    }


    @And("The admin on the Products Page")
    public void theAdminOnTheProductsPage() {
        actuatorAdmin.goToProductsPage();
    }


    @And("the admin selected specific product")
    public void theAdminSelectedSpecificProduct(){
        actuatorAdmin.findProductInProducts(productName, productModel);
    }


    @When("The admin hides the product")
    public void theAdminHidesTheProduct() {
        actuatorAdmin.hideTopProduct();
    }


    @Then("check product is hidden from catalog")
    public void checkProductIsHiddenFromCatalog(){
        actuatorAdmin.gotASuccessMessage();
    }
}
