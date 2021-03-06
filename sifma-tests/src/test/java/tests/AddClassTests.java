package tests;

import models.Advisor;
import models.Class;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddClassTests extends TestBase{

    @Test
    public void addClass(){
        SoftAssert asert=new SoftAssert();
        Class new_class = new Class().withName("Name"+ System.currentTimeMillis()).withGrade("Middle School")
                .withNum_students("20").withNum_teams_requested("5");
        Advisor advisor = new Advisor().withId(798519).withLast_name("Kramer");
        app.getCoordinatorsHelper().loginAsCoordinator("SIA", "wak46");
        app.getCoordinatorsHelper().goToSubMenu("Admin","Advisors");
        app.getCoordinatorsHelper().searchForAdvisor(advisor);
        advisor.withNum_classes(app.getCoordinatorsHelper().getClassesNum(advisor));
        advisor.withNum_teams(app.getCoordinatorsHelper().getTeamsNum((advisor)));
        app.getCoordinatorsHelper().openClassesForAdvisor(advisor);
        app.getClassHelper().addClass(new_class, advisor);
        app.getCoordinatorsHelper().returnToAdvSearch();
        //Assert.assertEquals(advisor.getNum_classes(), app.getCoordinatorsHelper().getClassesNum(advisor));
        //Assert.assertEquals(advisor.getNum_teams_requested(), app.getCoordinatorsHelper().getTeamsNum(advisor));
        asert.assertAll();
    }
}
