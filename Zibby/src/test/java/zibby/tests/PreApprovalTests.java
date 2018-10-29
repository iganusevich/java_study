package zibby.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class PreApprovalTests extends TestBase{

    @Test
    public void preApprovalTests() throws IOException, InterruptedException {
        String phone = app.phoneInfo().getPhone();
        app.customer().initiatePreApproval(phone);
        app.customer().isPhoneValid();
        String code = app.phoneInfo().getCode(phone);
        app.customer().enterCode(code);

        int i = 1;



        

    }

}
