package mantis.tests;

import mantis.models.MailMessage;
import mantis.models.User;
import mantis.models.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTest extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }



    @Test
    public void testPasswordChange() throws IOException, MessagingException {

        String adminUser = "administrator";
        String adminPassword = "root";
        String password = "root1";
        User user = app.db().users().iterator().next();
        app.session().login(adminUser, adminPassword);
        app.navigateTo().manage();
        app.navigateTo().manageUsers();
        app.manageUsers().resetPassword(user.getUsername());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetLink = app.mail().findLink(mailMessages, user.getEmail());
        app.registration().finish(resetLink, password);
        assertTrue(app.newSession().login(user.getUsername(), password));
        }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }




}
