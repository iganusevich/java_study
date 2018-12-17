package appmanager;

import models.Advisor;
import models.Class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CoordinatorsHelper extends HelperBase {

    public CoordinatorsHelper(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    //Navigation through Coordinator site
    public void loginAsCoordinator(String login, String password) {
        wd.navigate().to(String.format("http://admin.smgny.org/login?login=%1$s&password=%2$s",login,password));
        wait.until(titleIs("Home"));
    }

    public void goToSubMenu(String menu, String sub_Menu){
        hoverOverElement(getMenuElement(menu));
        wait.until(ExpectedConditions.visibilityOf(getMenuElement(menu)
                .findElement(By.xpath(String.format(".//span[.='%s']", sub_Menu)))));
        getMenuElement(menu).findElement(By.xpath(String.format(".//span[.='%s']", sub_Menu))).click();
    }

    public void openClassesForAdvisor(Advisor advisor){
        wd.findElement(By.cssSelector("a[href*='classes.xhtml?advisorId=" + advisor.getId() + "']")).click();
    }

    public void returnToAdvSearch(){
        wd.findElement(By.cssSelector("div[role=menu] a[href*=advisors]")).click();
        wait.until(ExpectedConditions.titleIs("Advisors"));
    }

    


    //Checks
    public void checkPages() {
        List<String> menu_items = wd.findElements(By.cssSelector("a.ui-submenu-link span.ui-menuitem-text"))
                .stream().map((e)->e.getText()).collect(Collectors.toList());
        for(String item : menu_items){
            if ((item.length() > 0) & (!item.equals("External Resources"))) {
                clickSubMenuItems(item);
            }
        }
    }


    //Helpers
    private void clickSubMenuItems(String item) {
        Actions builder = new Actions(wd);
        WebElement menu_item_before = getMenuElement(item);
        builder.moveToElement(menu_item_before).perform();
        WebElement menu_item_after = getMenuElement(item);
        List<String> sub_items = menu_item_after.findElements(By.cssSelector("a:not(.ui-submenu-link) span"))
                .stream().map((i)->i.getText()).collect(Collectors.toList());
        for (String sub_item : sub_items){
            if(sub_item.length()>0) {
                WebElement menu_item = getMenuElement(item);
                builder.moveToElement(menu_item).perform();
                WebElement menu_item_new = getMenuElement(item);
                menu_item_new.findElement(By.xpath(String.format(".//span[.='%s']", sub_item))).click();
            }
        }
    }

    private WebElement getMenuElement(String item) {
        return wd.findElement(By.xpath(String.format("//span[.='%s']/../..",item)));
    }



    public void searchForAdvisor(Advisor advisor){
        wd.findElement(By.cssSelector("input[id*=advisors_search]")).sendKeys(advisor.getLast_name());
        wd.findElement(By.xpath("//span[.='by Last Name']/..")).click();
    }

    public String getClassesTeamsNum(Advisor advisor){
        return wd.findElement(By.cssSelector("a[href*='classes.xhtml?advisorId=" + advisor.getId() + "']")).getText();
    }

    public int getClassesNum(Advisor advisor){
        return Integer.parseInt(getClassesTeamsNum(advisor).split("/")[0]);
    }
    
    public int getTeamsNum(Advisor advisor){
        return Integer.parseInt(getClassesTeamsNum(advisor).split("/")[1]);
    }


}
