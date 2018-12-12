package tests;


import appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;


@Listeners(MyTestListener.class)
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite  (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
