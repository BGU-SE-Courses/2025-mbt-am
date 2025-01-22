/* @provengo summon selenium */

// User behavior
bthread("userBehavior", function () {
    let s = new SeleniumSession("userSession");
    s.start(XPATH_LOCATORS.USER.URL);
    goToFirstProductInPage(s, XPATH_LOCATORS.USER);
    goToReviews(s, XPATH_LOCATORS.USER);
    let userData = Object.assign({}, XPATH_LOCATORS.USER, CREDENTIALS.USER);
    writeAReview(s, userData);
    gotASuccessMessage(s, XPATH_LOCATORS.USER);
    sync({ request: Event("UserReviewSuccess") }); // Signal the user success
    s.close();
});

// Admin behavior
bthread("adminBehavior", function () {
    let s = new SeleniumSession("adminSession");
    s.start(XPATH_LOCATORS.ADMIN.URL);
    let adminData = Object.assign({}, XPATH_LOCATORS.ADMIN, CREDENTIALS.ADMIN);
    logInToAdmin(s, adminData);
    goToProductsPage(s, XPATH_LOCATORS.ADMIN);
    findProductInProducts(s, XPATH_LOCATORS.ADMIN, "MacBook", "Product 16");
    sync({ waitFor: Event("UserReviewSuccess") }); // Wait for user review success
    hideProduct(s, XPATH_LOCATORS.ADMIN);
    gotASuccessMessage(s, XPATH_LOCATORS.ADMIN);
    s.close();
});

