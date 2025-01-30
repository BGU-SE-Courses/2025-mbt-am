// @provengo summon ctrl

/**
 * List of events "of interest" that we want test suites to cover.
 */
const GOALS = [
    ["sUser.start", "goToFirstProductInPage"],
    ["goToFirstProductInPage", "goToReviews"],
    ["goToReviews", "writeAReview"],
    ["writeAReview", "gotASuccessMessage_user"],
    ["gotASuccessMessage_user", "sync_UserReviewSuccess"],
    ["sync_UserReviewSuccess", "sUser.close"],
    ["sAdmin.start", "logInToAdmin"],
    ["logInToAdmin", "goToProductsPage"],
    ["goToProductsPage", "findProductInProducts"],
    ["findProductInProducts", "sync_WaitForUserReviewSuccess"],
    ["sync_WaitForUserReviewSuccess", "hideProduct"],
    ["hideProduct", "gotASuccessMessage_admin"],
    ["gotASuccessMessage_admin", "sAdmin.close"],
    ["sUser.start", "sAdmin.start"],
    ["sUser.start", "logInToAdmin"],
    ["sUser.start", "goToProductsPage"],
    ["sUser.start", "findProductInProducts"],
    ["sUser.start", "sync_WaitForUserReviewSuccess"],
    ["goToFirstProductInPage", "sAdmin.start"],
    ["goToFirstProductInPage", "logInToAdmin"],
    ["goToFirstProductInPage", "goToProductsPage"],
    ["goToFirstProductInPage", "findProductInProducts"],
    ["goToFirstProductInPage", "sync_WaitForUserReviewSuccess"],
    ["goToReviews", "sAdmin.start"],
    ["goToReviews", "logInToAdmin"],
    ["goToReviews", "goToProductsPage"],
    ["goToReviews", "findProductInProducts"],
    ["goToReviews", "sync_WaitForUserReviewSuccess"],
    ["writeAReview", "sAdmin.start"],
    ["writeAReview", "logInToAdmin"],
    ["writeAReview", "goToProductsPage"],
    ["writeAReview", "findProductInProducts"],
    ["writeAReview", "sync_WaitForUserReviewSuccess"],
    ["gotASuccessMessage_user", "sAdmin.start"],
    ["gotASuccessMessage_user", "logInToAdmin"],
    ["gotASuccessMessage_user", "goToProductsPage"],
    ["gotASuccessMessage_user", "findProductInProducts"],
    ["gotASuccessMessage_user", "sync_WaitForUserReviewSuccess"],
    ["sync_UserReviewSuccess", "sAdmin.start"],
    ["sync_UserReviewSuccess", "logInToAdmin"],
    ["sync_UserReviewSuccess", "goToProductsPage"],
    ["sync_UserReviewSuccess", "findProductInProducts"],
    ["sync_UserReviewSuccess", "sync_WaitForUserReviewSuccess"],
    ["sUser.close", "sAdmin.start"],
    ["sUser.close", "logInToAdmin"],
    ["sUser.close", "goToProductsPage"],
    ["sUser.close", "findProductInProducts"],
    ["sUser.close", "sync_WaitForUserReviewSuccess"],
    ["sUser.close", "hideProduct"],
    ["sUser.close", "gotASuccessMessage_admin"],
    ["sUser.close", "sAdmin.close"],
    ["sAdmin.start", "sUser.start"],
    ["sAdmin.start", "goToFirstProductInPage"],
    ["sAdmin.start", "goToReviews"],
    ["sAdmin.start", "writeAReview"],
    ["sAdmin.start", "gotASuccessMessage_user"],
    ["sAdmin.start", "sync_UserReviewSuccess"],
    ["sAdmin.start", "sUser.close"],
    ["logInToAdmin", "sUser.start"],
    ["logInToAdmin", "goToFirstProductInPage"],
    ["logInToAdmin", "goToReviews"],
    ["logInToAdmin", "writeAReview"],
    ["logInToAdmin", "gotASuccessMessage_user"],
    ["logInToAdmin", "sync_UserReviewSuccess"],
    ["logInToAdmin", "sUser.close"],
    ["goToProductsPage", "sUser.start"],
    ["goToProductsPage", "goToFirstProductInPage"],
    ["goToProductsPage", "goToReviews"],
    ["goToProductsPage", "writeAReview"],
    ["goToProductsPage", "gotASuccessMessage_user"],
    ["goToProductsPage", "sync_UserReviewSuccess"],
    ["goToProductsPage", "sUser.close"],
    ["findProductInProducts", "sUser.start"],
    ["findProductInProducts", "goToFirstProductInPage"],
    ["findProductInProducts", "goToReviews"],
    ["findProductInProducts", "writeAReview"],
    ["findProductInProducts", "gotASuccessMessage_user"],
    ["findProductInProducts", "sync_UserReviewSuccess"],
    ["findProductInProducts", "sUser.close"],
    ["sync_WaitForUserReviewSuccess", "sUser.start"],
    ["sync_WaitForUserReviewSuccess", "goToFirstProductInPage"],
    ["sync_WaitForUserReviewSuccess", "goToReviews"],
    ["sync_WaitForUserReviewSuccess", "writeAReview"],
    ["sync_WaitForUserReviewSuccess", "gotASuccessMessage_user"],
    ["sync_WaitForUserReviewSuccess", "sync_UserReviewSuccess"],
    ["sync_WaitForUserReviewSuccess", "sUser.close"],
    ["hideProduct", "sUser.close"],
    ["gotASuccessMessage_admin", "sUser.close"],
    ["sAdmin.close", "sUser.close"]
];

const makeGoals = function(){
    return [ [ any(/Howdy/), any(/Venus/) ],
             [ any(/Mars/) ],
             [ Ctrl.markEvent("Classic!") ] ];
}

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more than once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS.length; idx++ ) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if ( unreachedGoal.contains(event) ) {
                    unreachedGoals.splice(ugIdx,1);
                }
            }
        }
    }

    return GOALS.length-unreachedGoals.length;
}

/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the 
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
 function rankingFunction(ensemble) {
    
    // How many goals did `ensemble` hit?
    const metGoalsCount = rankByMetGoals(ensemble);
    // What percentage of the goals did `ensemble` cover?
    const metGoalsPercent = metGoalsCount/GOALS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}

