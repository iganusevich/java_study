package appmanager;

import models.Advisor;
import models.Class;
import models.ClassSet;
import models.Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdvisorHelper extends HelperBase {
    public AdvisorHelper(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public void loginAsAdvisor(Advisor advisor) {
        //wd.navigate().to("http://ww3.smgny.org/login.html");
        wd.navigate().to("https://www.stockmarketgame.org/login.html");
        wd.findElement(By.cssSelector("input[name=ACCOUNTNO]")).sendKeys(advisor.getLogin());
        wd.findElement(By.cssSelector("input[name=USER_PIN]")).sendKeys(advisor.getPassword());
        wd.findElement(By.cssSelector("input[value=Login]")).click();
        //wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("a#take-tour"))));
    }

    public void closeNewsPopUp() {
        String mainWindow = wd.getWindowHandle();
        Set<String> oldWindows = wd.getWindowHandles();
        for (String window : oldWindows){
            if(!window.equals(mainWindow)){
                wd.switchTo().window(window);
                Assert.assertTrue(isElementPresent
                        (By.xpath("//td[text() = 'Page 1 News, From your Local Coordinator']")));
                wd.close();
                wd.switchTo().window(mainWindow);
            }
        }
    }

    public String viewRankings() {
        List<String> oldWindows = new ArrayList<>(wd.getWindowHandles());
        wd.findElement(By.cssSelector("a[href*=viewrankings]")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
        List<String> new_windows = getNewWindows(oldWindows);
        wd.switchTo().window(new_windows.get(0));
        return new_windows.get(0);
    }


    public String getCurrentWindow() {
        return wd.getWindowHandle();
    }

    public void checkClassPopUp(Advisor advisor, SoftAssert asert) {
        String viewRankings = wd.getWindowHandle();
        List<String> oldWindows = new ArrayList<>(wd.getWindowHandles());
        for(Object some_class : advisor.getClasses()){
            wd.findElement(By.xpath(String.format("//a[.='%s']", ((Class) some_class).getName()))).click();
            List<String> new_windows = getNewWindows(oldWindows);
            wd.switchTo().window(new_windows.get(0));
            String requested_teams = extractRegEx("([0-9]+)",
                    wd.findElement(By.xpath("//font[contains(text(),'Team Info')]")).getText());
            String assigned_teams = extractRegEx("([0-9]+)",
                    wd.findElement(By.xpath("//font[contains(text(),'Assigned')]")).getText());
            asert.assertEquals(((Class) some_class).getNum_teams_requested(),requested_teams);
            asert.assertEquals(((Class) some_class).getNum_teams_assigned(),assigned_teams);
            wd.close();
            wd.switchTo().window(viewRankings);
        }
    }

    public void getAdvisorClasses(Advisor advisor) {
        List<String> class_names = wd.findElements(By.cssSelector("a[style*=text]")).stream()
                .map((e)->e.getText()).collect(Collectors.toList());
        advisor.withLast_name(wd.findElement(By.cssSelector("div#dvAdvName")).getText());
        advisor.withClasses(createClassList(class_names));
    }

    private ClassSet createClassList(List<String> class_names) {
        ClassSet classes = new ClassSet();
        for(String name : class_names){
            Class newClass = new Class().withName(name);
            String team_requested = wd.findElement(By
                    .xpath(String.format("//a[.='%s']/../following-sibling::td[1]", name))).getText();
            List<WebElement> teams = wd.findElements(By
                    .xpath(String.format("//a[.='%s']/../following-sibling::td[2]/a", name)));
            int teams_assigned = teams.size();
            newClass.withNum_teams_assigned(Integer.toString(teams_assigned)).withNum_teams_requested(team_requested);
            if (teams_assigned>0){
                List<Team> class_teams = new ArrayList<>();
                for (WebElement team : teams){
                    Team new_team = new Team();
                    String attr = team.getAttribute("onclick");
                    new_team.withLogin(extractRegEx("\\(\"([A-Z,0-9,_]*)", attr))
                            .withPassword(extractRegEx(",\"([A-Z,0-9]*)", attr));
                    class_teams.add(new_team);
                }
                newClass.addTeams((class_teams));
            }
            classes.withClass(newClass);
        }
        return classes;
    }


    public void checkLogInAsTeam(Advisor advisor, SoftAssert asert) {
        for(Object some_class : advisor.getClasses()){
            String mainWindow = wd.getWindowHandle();

            if( ((Class) some_class).getTeams().size() > 0){
                switchToNewWindow("//a[. = '%s']",((Class) some_class).getTeams().iterator().next().getLogin());
                wait.until(ExpectedConditions.visibilityOf(wd.findElement(By
                        .xpath("//span[@id = 'hdrlblAdvisor'][. = 'Willard Kramer']"))));
                asert.assertEquals(((Class) some_class).getTeams().iterator().next()
                        .getLogin(), wd.findElement(By.cssSelector("span#lblTeam")).getText());
                wd.close();
                wd.switchTo().window(mainWindow);
            }
        }
    }



}
