/**
 * Opens the OpenCart URL for the user.
 * @param {SeleniumSession} session - The Selenium session.
 */
function openCart(session) {
  session.start(XPATH_LOCATORS.USER.URL); // Access the URL for the USER
  session.executeScript("window.moveTo(0, 0); window.resizeTo(screen.width, screen.height);"); // Maximize window
}

/**
 * Navigates to the first product on the product page.
 * @param {SeleniumSession} session - The Selenium session.
 */
function goToFirstProductInPage(session) {
  session.click(XPATH_LOCATORS.USER.FIRST_PRODUCT_IN_PAGE); // Access the first product locator
}

/**
 * Navigates to the reviews section of the product.
 * @param {SeleniumSession} session - The Selenium session.
 */
function goToReviews(session) {
  session.click(XPATH_LOCATORS.USER.REVIEWS_TAB); // Access the reviews tab locator
}

/**
 * Writes a review by filling in the name, text, and rating.
 * @param {SeleniumSession} session - The Selenium session.
 * @param {string} fullName - The reviewer's full name.
 * @param {string} reviewText - The review text.
 * @param {number} rating - The rating (1 to 5).
 */
function writeAReview(session, fullName, reviewText, rating) {
  session.executeScript("document.body.style.zoom='50%';"); // Zoom out the page  session.writeText(XPATH_LOCATORS.USER.FULL_NAME, fullName); // Fill full name
  session.writeText(XPATH_LOCATORS.USER.REVIEW_TEXT_BOX, reviewText); // Fill review text
  const ratingXPath = XPATH_LOCATORS.USER.RATING_SCALE.replace("x", String(rating - 1)); // Replace x for rating
  session.click(ratingXPath); // Select rating
  session.click(XPATH_LOCATORS.USER.CONTINUE_BUTTON); // Submit the review
}

/**
 * Waits for the success message to be displayed after a review submission.
 * @param {SeleniumSession} session - The Selenium session.
 */
function gotASuccessMessage(session) {
  session.waitForElement(XPATH_LOCATORS.USER.SUCCESS_ALERT); // Wait for success message
}

