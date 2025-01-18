package hellocucumber;

public class SeleniumExample {
    public static void main(String[] args) throws InterruptedException {
        OpenCartActuatorUser registration = new OpenCartActuatorUser();

        registration.openCart();
        registration.enlargeWindow();
        registration.goToMacBookProduct();
        registration.scrollToReviews();
        registration.goToReviews();
        registration.writeAReview("test1", "The MacBook is a premium laptop with excellent performance, stunning display quality, and long battery life, making it ideal for professionals.", 5 );
        registration.closeBrowser();


    }

}