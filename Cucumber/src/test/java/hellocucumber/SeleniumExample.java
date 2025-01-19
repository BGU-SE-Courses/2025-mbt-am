package hellocucumber;

import hellocucumber.OpenCartActuatorUser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SeleniumExample {
    public static void main(String[] args) throws InterruptedException {
        OpenCartActuatorUser user = new OpenCartActuatorUser();

        user.openCart();
        user.enlargeWindow();
        user.goToMacBookProduct();
        user.scrollToReviews();
        user.goToReviews();
        user.writeAReview("test1", "The MacBook is a premium laptop with excellent performance, stunning display quality, and long battery life, making it ideal for professionals.", 5 );
        user.closeBrowser();

        OpenCartActuatorAdmin admin = new OpenCartActuatorAdmin();
        admin.openCartAdmin();
        admin.enlargeWindow();
        admin.LogInToAdmin("admin", "1234");
        admin.closeBrowser();

    }

}
