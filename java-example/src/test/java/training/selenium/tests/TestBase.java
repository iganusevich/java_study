package training.selenium.tests;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import training.selenium.appmanagaer.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @Before
    public void start() {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }
}