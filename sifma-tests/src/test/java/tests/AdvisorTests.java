package tests;

import models.Advisor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvisorTests extends TestBase {


    @Test
    public void advisorLogsInAsPlayer(){
        SoftAssert asert=new SoftAssert();
        Advisor advisor = new Advisor().withLogin("SIA_99_T798519").withPassword("BQXA4927"); // Prod: SIA_99_T798519 / BQXA4927
        app.getAdvisorHelper().loginAsAdvisor(advisor);
        app.getAdvisorHelper().closeNewsPopUp();
        String mainWindow = app.getAdvisorHelper().getCurrentWindow();
        String viewRankingsWindow = app.getAdvisorHelper().viewRankings();
        app.getAdvisorHelper().getAdvisorClasses(advisor);
        app.getAdvisorHelper().checkClassPopUp(advisor, asert);
        //app.getAdvisorHelper().checkLogInAsTeam(advisor, asert);
        asert.assertAll();
    }
}
