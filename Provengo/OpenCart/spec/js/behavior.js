/* @provengo summon selenium */
/* @provengo include ./actions.js */

/**
 * Behavior: User writes a review for a product.
 */
bthread("User Writes a Review", function () {
    // Start a new Selenium session for the user
    let session = new SeleniumSession("userReview");

    // Open the OpenCart site and maximize the window
    actions.openCart(session);

    // Navigate to the first product on the page
    actions.goToFirstProductInPage(session);

    // Navigate to the reviews tab of the product
    actions.goToReviews(session);

    // Write a review with full name, review text, and rating
    actions.writeAReview(session, "John Doe", "Amazing product! Highly recommend.", 5);

    // Wait for the success message
    actions.gotASuccessMessage(session);

    // End the session
    session.end();
});


