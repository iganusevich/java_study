package mantis.tests;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test 
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }

    @Test  (enabled = false)
    public void testCurDir(){
        File current = new File(".");
        System.out.println(current.getAbsolutePath());
    }
}
