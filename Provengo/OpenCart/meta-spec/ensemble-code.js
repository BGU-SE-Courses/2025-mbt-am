/**
 * List of events "of interest" that we want test suites to cover.
 */

const makeGoalsForDomainSpecific = function() {
    return [
        Ctrl.markEvent("sUser.start", "goToFirstProductInPage"),
        Ctrl.markEvent("goToFirstProductInPage", "goToReviews"),
        Ctrl.markEvent("goToReviews", "writeAReview"),
        Ctrl.markEvent("writeAReview", "gotASuccessMessage_user"),
        Ctrl.markEvent("gotASuccessMessage_user", "sync_UserReviewSuccess"),
        Ctrl.markEvent("sync_UserReviewSuccess", "sUser.close"),
        Ctrl.markEvent("sAdmin.start", "logInToAdmin"),
        Ctrl.markEvent("logInToAdmin", "goToProductsPage"),
        Ctrl.markEvent("goToProductsPage", "findProductInProducts"),
        Ctrl.markEvent("findProductInProducts", "sync_WaitForUserReviewSuccess"),
        Ctrl.markEvent("sync_WaitForUserReviewSuccess", "hideProduct"),
        Ctrl.markEvent("hideProduct", "gotASuccessMessage_admin"),
        Ctrl.markEvent("gotASuccessMessage_admin", "sAdmin.close")
    ];
}

const makeGoalsForTwoWay = function() {
    let goals = makeGoalsForDomainSpecific();
    goals.push(
        Ctrl.markEvent("sUser.start", "sAdmin.start"),
        Ctrl.markEvent("sUser.start", "logInToAdmin"),
        Ctrl.markEvent("sUser.start", "goToProductsPage"),
        Ctrl.markEvent("sUser.start", "findProductInProducts"),
        Ctrl.markEvent("sUser.start", "sync_WaitForUserReviewSuccess")
    );
    return goals;
}

const GOALS = makeGoalsForDomainSpecific();

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 *
 * It makes no difference if a goal was met more than once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals(ensemble) {
    const unreachedGoals =  Object.assign({}, GOALS);

    for (let test of ensemble) {
        for (let event of test) {
            for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if (unreachedGoal.includes(event)) {
                    unreachedGoals.splice(ugIdx, 1);
                }
            }
        }
    }

    return GOALS.length - unreachedGoals.length;
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
    const metGoalsPercent = metGoalsCount / GOALS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}



// /**
//  * List of events "of interest" that we want test suites to cover.
//  */
// const GOALS = [
//     any(/Howdy/),
//     any(/Mars/),
//     Ctrl.markEvent("Classic!")
// ];
//
// const makeGoals = function(){
//     return [ [ any(/Howdy/), any(/Venus/) ],
//         [ any(/Mars/) ],
//         [ Ctrl.markEvent("Classic!") ] ];
// }
//
// /**
//  * Ranks test suites by how many events from the GOALS array were met.
//  * The more goals are met, the higher the score.
//  *
//  * It make no difference if a goal was met more than once.
//  *
//  * @param {Event[][]} ensemble The test suite to be ranked.
//  * @returns Number of events from GOALS that have been met.
//  */
// function rankByMetGoals( ensemble ) {
//     const unreachedGoals = [];
//     for ( let idx=0; idx<GOALS.length; idx++ ) {
//         unreachedGoals.push(GOALS[idx]);
//     }
//
//     for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
//         let test = ensemble[testIdx];
//         for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
//             let event = test[eventIdx];
//             for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
//                 let unreachedGoal = unreachedGoals[ugIdx];
//                 if ( unreachedGoal.contains(event) ) {
//                     unreachedGoals.splice(ugIdx,1);
//                 }
//             }
//         }
//     }
//
//     return GOALS.length-unreachedGoals.length;
// }
//
// /**
//  * Ranks potential test suites based on the percentage of goals they cover.
//  * Goal events are defined in the GOALS array above. An ensemble with rank
//  * 100 covers all the goal events.
//  *
//  * Multiple ranking functions are supported - to change ranking function,
//  * use the ensemble.ranking-function configuration key, or the
//  * --ranking-function <functionName> command-line parameter.
//  *
//  * @param {Event[][]} ensemble the test suite/ensemble to be ranked
//  * @returns the percentage of goals covered by ensemble.
//  */
// function rankingFunction(ensemble) {
//
//     // How many goals did ensemble hit?
//     const metGoalsCount = rankByMetGoals(ensemble);
//     // What percentage of the goals did ensemble cover?
//     const metGoalsPercent = metGoalsCount/GOALS.length;
//
//     return metGoalsPercent * 100; // convert to human-readable percentage
// }
