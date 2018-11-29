package training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import training.selenium.appmanagaer.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();




    @BeforeSuite
    public void start() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}