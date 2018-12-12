package tests;

import models.Advisor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteClassTests extends TestBase{

    @Test
    public void deleteClass(){
        SoftAssert asert=new SoftAssert();
        Advisor advisor = new Advisor().withId(798519).withLast_name("Kramer");
        app.getCoordinatorsHelper().loginAsCoordinator("SIA", "wak46");
        app.getCoordinatorsHelper().goToSubMenu("Admin","Advisors");
        app.getCoordinatorsHelper().searchForAdvisor(advisor);
        advisor.withNum_classes(app.getCoordinatorsHelper().getClassesNum(advisor));
        advisor.withNum_teams(app.getCoordinatorsHelper().getTeamsNum((advisor)));
        app.getCoordinatorsHelper().openClassesForAdvisor(advisor);
        app.getClassHelper().deleteLastClass(advisor);
        app.getCoordinatorsHelper().returnToAdvSearch();
        asert.assertEquals(advisor.getNum_classes(), app.getCoordinatorsHelper().getClassesNum(advisor));
        asert.assertEquals(advisor.getNum_teams(), app.getCoordinatorsHelper().getTeamsNum(advisor));
        asert.assertAll();
    }
}
