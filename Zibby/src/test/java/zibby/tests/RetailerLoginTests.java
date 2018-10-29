package zibby.tests;

import org.testng.annotations.Test;

public class RetailerLoginTests extends TestBase {



    @Test
    public void retailerLoginTests() {
        app.retailer().retailerLogin("nadim.cognical","test");
        app.retailer().openApplication(Integer.toString(590947));

    }
    

}
