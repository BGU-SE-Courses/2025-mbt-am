package hellocucumber;

public class SeleniumExampleAdmin
{
    public static void main(String[] args) throws InterruptedException {
        OpenCartActuatorAdmin registration = new OpenCartActuatorAdmin();

        registration.openCartAdmin();
        registration.enlargeWindow();
        registration.enterLoginInfoAdmin("admin","1234");
//        registration.goToMacBookProduct();
//        registration.scrollToReviews();
//        registration.goToReviews();
//        registration.writeAReview("test1", "The MacBook is a premium laptop with excellent performance, stunning display quality, and long battery life, making it ideal for professionals.", 5 );
        registration.closeBrowser();


    }

}
