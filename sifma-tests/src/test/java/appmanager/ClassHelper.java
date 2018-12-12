package appmanager;

import models.Advisor;
import models.Class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassHelper extends HelperBase {
    public ClassHelper(WebDriver wd, WebDriverWait wait) {
        super(wd,wait);
    }

    public void addClass(Class new_class, Advisor advisor){
        wd.findElement(By.cssSelector("input[id*=class_name] ")).sendKeys(new_class.getName());
        wd.findElement(By.cssSelector("div[id='formClass:j_idt145'] div[class*=ui-selectonemenu-trigger]")).click();
        wd.findElement(By.cssSelector("li[data-label='Middle School']")).click();
        wd.findElement(By.cssSelector("input[id='formClass:noOfParticipants']")).sendKeys(new_class.getNum_students());
        wd.findElement(By.cssSelector("input[id='formClass:noOfTeams']")).sendKeys(new_class.getNum_teams_requested());
        wd.findElement(By.xpath("//span[.='Add Class'][@class='ui-button-text ui-c']/..")).click();
        advisor.incNumClassesBy(1);
        advisor.incNumTeamsBy(Integer.parseInt(new_class.getNum_teams_requested()));
    }

    public void deleteLastClass(Advisor advisor) {
        Class class_to_del = new Class().withId(getLastClassId());
        deleteClass(class_to_del, advisor);
        wait.until(ExpectedConditions.titleIs("Classes"));
    }

    private void deleteClass(Class class_to_del, Advisor advisor) {
        WebElement class_record = wd.findElement(By
                .xpath("//a[contains(@href, 'classId="+ class_to_del.getId() +"')]/../.."));
        String text = class_record.findElement(By.xpath("//div[contains(text(),'Requested teams')]")).getText();
        wd.findElement(By.cssSelector("a[href*='classId="+ class_to_del.getId() +"']")).click();
        wd.findElement(By.xpath("//span[.='Delete Class']/..")).click();
        wd.findElement(By.xpath("//span[.='Yes']/..")).click();
        advisor.decNumClassesBy(1);
        advisor.decNumTeamsBy(Integer.parseInt(extractRegEx(": ([0-9]*)",text)));
//        advisor.withNum_classes(advisor.getNum_classes() - 1);
//        advisor.withNum_teams(advisor.getNum_teams_requested() - Integer.parseInt(extractRegEx(": ([0-9]*)",text)));
    }

    private int getLastClassId() {
        return wd.findElements(By.cssSelector("a[href*=classId]")).stream()
                .map((e)-> Integer.parseInt(extractRegEx( "classId=([0-9]*)",e.getAttribute("href"))))
                .mapToInt((w)-> w ).max().getAsInt();
    }


}
