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

    // Calculate the XPath for the rating input based on the given rating
    const ratingKey = `RATING_SCALE_${data.RATING}`; // Dynamically create the key
    const ratingXPath = data[ratingKey]; // Retrieve the XPath for the rating

    scrollToElement(data.CONTINUE_BUTTON);
    click(ratingXPath); // Select rating
    click(data.CONTINUE_BUTTON); // Submit the review
    sync({ request: Event("End(writeAReview)") });
  }
}

function gotASuccessMessage(session, data) {
  with (session) {
    sync({ request: Event("Begin(gotASuccessMessage)") });
    waitForVisibility(data.SUCCESS_ALERT, 5000); // Wait for alert to become visible
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
    scrollToBottom();
    click(data.FILTER_BUTTON); // Apply filter
    scrollToTop();
    sync({ request: Event("End(findProductInProducts)") });
  }
}

function hideProduct(session, data) {
  with (session) {
    sync({ request: Event("Begin(hideProduct)") });
    click(data.EDIT_BUTTON); // Open product edit page
    click(data.DATA_TAB_BUTTON); // Go to data tab
    scrollToElement(data.STATUS_TOGGLE);
    click(data.STATUS_TOGGLE); // Toggle status to disable/delete
    scrollToElement(data.SAVE_BUTTON);
    click(data.SAVE_BUTTON); // Save changes
    sync({ request: Event("End(hideProduct)") });
  }
}



