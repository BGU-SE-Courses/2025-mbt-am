function goToFirstProductInPage(session, data) {
  with (session) {
    sync({ request: Event("Begin(goToFirstProductInPage)") });
    click(data.FIRST_PRODUCT_IN_PAGE); // Access the first product locator
    sync({ request: Event("End(goToFirstProductInPage)") });
  }
}

function goToReviews(session, data) {
  with (session) {
    sync({ request: Event("Begin(goToReviews)") });
    click(data.REVIEWS_TAB); // Access the reviews tab locator
    sync({ request: Event("End(goToReviews)") });
  }
}

function writeAReview(session, data) {
  with (session) {
    sync({ request: Event("Begin(writeAReview)") });
    writeText(data.FULL_NAME_BOX, data.FULL_NAME); // Fill full name
    writeText(data.REVIEW_TEXT_BOX, data.REVIEW); // Fill review text
    const ratingXPath = data.RATING_SCALE.replace("x", String(data.RATING - 1)); // Replace x for rating
    click(ratingXPath); // Select rating
    click(data.CONTINUE_BUTTON); // Submit the review
    sync({ request: Event("End(writeAReview)") });
  }
}

function gotASuccessMessage(session, data) {
  with (session) {
    sync({ request: Event("Begin(gotASuccessMessage)") });
    waitForInvisibility(data.SUCCESS_ALERT); // Wait for success message
    sync({ request: Event("End(gotASuccessMessage)") });
  }
}

function logInToAdmin(session, data) {
  with (session) {
    sync({ request: Event("Begin(logInToAdmin)") });
    writeText(data.USERNAME_BOX, data.USERNAME); // Fill admin username
    writeText(data.PASSWORD_BOX, data.PASSWORD); // Fill admin password
    click(data.CONTINUE_BUTTON); // Click login
    click(data.EXIT_WARNING_BUTTON); // Handle any warnings
    sync({ request: Event("End(logInToAdmin)") });
  }
}

function goToProductsPage(session, data) {
  with (session) {
    sync({ request: Event("Begin(goToProductsPage)") });
    click(data.CATALOG_BUTTON); // Open catalog
    click(data.PRODUCTS_BUTTON); // Go to products page
    sync({ request: Event("End(goToProductsPage)") });
  }
}

function findProductInProducts(session, data, productName, productModel) {
  with (session) {
    sync({ request: Event("Begin(findProductInProducts)") });
    writeText(data.PRODUCT_NAME_BOX, productName); // Input product name
    writeText(data.PRODUCT_MODEL_BOX, productModel); // Input product model
    click(data.FILTER_BUTTON); // Apply filter
    sync({ request: Event("End(findProductInProducts)") });
  }
}

function hideProduct(session, data) {
  with (session) {
    sync({ request: Event("Begin(hideProduct)") });
    click(data.EDIT_BUTTON); // Open product edit page
    click(data.DATA_TAB_BUTTON); // Go to data tab
    click(data.STATUS_TOGGLE); // Toggle status to disable/delete
    click(data.SAVE_BUTTON); // Save changes
    sync({ request: Event("End(hideProduct)") });
  }
}



