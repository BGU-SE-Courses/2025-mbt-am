/* @provengo summon selenium */

const sUser = new SeleniumSession("userSession");
const sAdmin = new SeleniumSession("adminSession");


// User behavior
bthread("userBehavior", function () {
    sUser.start(XPATH_LOCATORS.USER.URL);
    goToFirstProductInPage(sUser, XPATH_LOCATORS.USER);
    goToReviews(sUser, XPATH_LOCATORS.USER);
    let userData = Object.assign({}, XPATH_LOCATORS.USER, CREDENTIALS.USER);
    writeAReview(sUser, userData);
    gotASuccessMessage(sUser, XPATH_LOCATORS.USER);
    sync({ request: Event("UserReviewSuccess") }); // Signal the user success
    sUser.close();
});

// Admin behavior
bthread("adminBehavior", function () {
    // let s = new SeleniumSession("adminSession");
    sAdmin.start(XPATH_LOCATORS.ADMIN.URL);
    let adminData = Object.assign({}, XPATH_LOCATORS.ADMIN, CREDENTIALS.ADMIN);
    logInToAdmin(sAdmin, adminData);
    goToProductsPage(sAdmin, XPATH_LOCATORS.ADMIN);
    findProductInProducts(sAdmin, adminData);
    sync({ waitFor: Event("UserReviewSuccess") }); // Wait for user review success
    hideProduct(sAdmin, XPATH_LOCATORS.ADMIN);
    gotASuccessMessage(sAdmin, XPATH_LOCATORS.ADMIN);
    sAdmin.close();
});

