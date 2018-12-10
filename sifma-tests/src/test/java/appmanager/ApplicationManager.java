package appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    WebDriver wd;
    public WebDriverWait wait;
    private String browser;

    private ClassHelper classHelper;
    private CoordinatorsHelper coordinatorsHelper;
    private GamesHelper gamesHelper;
    private AdvisorHelper advisorHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
        
    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        }   else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        }   else if (browser.equals(BrowserType.IE)){
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 5);
        //wd.get(properties.getProperty("web.baseURL"));
        //login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
        classHelper = new ClassHelper(wd, wait);
        coordinatorsHelper = new CoordinatorsHelper(wd, wait);
        gamesHelper = new GamesHelper(wd, wait);
        advisorHelper = new AdvisorHelper(wd,wait);
        }

    public void stop() {
        wd.quit();
    }



    public ClassHelper getClassHelper() {
        return classHelper;
    }

    public CoordinatorsHelper getCoordinatorsHelper() {
        return coordinatorsHelper;
    }

    public GamesHelper getGamesHelper() {
        return gamesHelper;
    }

    public AdvisorHelper getAdvisorHelper() {return advisorHelper; }
}
