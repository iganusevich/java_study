package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Advisor;
import models.Class;
import models.Team;
import models.Trade;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SmokeTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> tradesFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/trades.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<Trade> trades = gson.fromJson(json, new TypeToken<List<Trade>>(){}.getType());
        return trades.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }


    @Test
    public void checkCoordinatorsPages(){
        app.getCoordinatorsHelper().loginAsCoordinator("SIA", "wak46");
        app.getCoordinatorsHelper().checkPages();
    }

    @Test
    public void addClass(){
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
    }

    @Test
    public void deleteClass(){
        Advisor advisor = new Advisor().withId(798519).withLast_name("Kramer");
        app.getCoordinatorsHelper().loginAsCoordinator("SIA", "wak46");
        app.getCoordinatorsHelper().goToSubMenu("Admin","Advisors");
        app.getCoordinatorsHelper().searchForAdvisor(advisor);
        advisor.withNum_classes(app.getCoordinatorsHelper().getClassesNum(advisor));
        advisor.withNum_teams(app.getCoordinatorsHelper().getTeamsNum((advisor)));
        app.getCoordinatorsHelper().openClassesForAdvisor(advisor);
        app.getClassHelper().deleteLastClass(advisor);
        app.getCoordinatorsHelper().returnToAdvSearch();
        Assert.assertEquals(advisor.getNum_classes(), app.getCoordinatorsHelper().getClassesNum(advisor));
        Assert.assertEquals(advisor.getNum_teams(), app.getCoordinatorsHelper().getTeamsNum(advisor));
    }

    @Test(dataProvider = "tradesFromJSON")
    public void makeTrades(Trade trade){
        Team team = new Team().withLogin("SIA_99_A629").withPassword("aqAwaEMl");//QA: SIA_99_A676/ 4AK4G8RD ; Prod: SIA_99_A629/ aqAwaEMl
        app.getGamesHelper().loginAsTeam(team);
        app.getGamesHelper().goToSubMenu("TRADE", "Enter a Trade");
        app.getGamesHelper().makeTrade(trade);
        app.getGamesHelper().confirmTrade(team, trade);
        Assert.assertTrue(trade.isConfirmed());
    }

    @Test
    public void advisorLogsInAsPlayer(){
        SoftAssert asert=new SoftAssert();
        Advisor advisor = new Advisor().withLogin("SIA_99_T798519/").withPassword("BQXA4927"); // Prod: SIA_99_T798519 / BQXA4927
        app.getAdvisorHelper().loginAsAdvisor(advisor);
        app.getAdvisorHelper().closeNewsPopUp();
        String mainWindow = app.getAdvisorHelper().getCurrentWindow();
        String viewRankingsWindow = app.getAdvisorHelper().viewRankings();
        app.getAdvisorHelper().getAdvisorClasses(advisor);
        app.getAdvisorHelper().checkClassPopUp(advisor, asert);
        //app.getAdvisorHelper().checkLogInAsTeam(advisor, asert);
        asert.assertAll();
    }

    @Test
    public void test(){
        Class old_class = new Class().withId(12345);
        Class new_class = new Class(old_class);
        new_class.withId(5);

        int i = 0;

    }

}
