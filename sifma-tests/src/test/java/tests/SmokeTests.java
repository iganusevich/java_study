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
        SoftAssert asert=new SoftAssert();
        app.getCoordinatorsHelper().loginAsCoordinator("SIA", "wak46");
        app.getCoordinatorsHelper().checkPages();
        asert.assertAll();
    }





    @Test(dataProvider = "tradesFromJSON")
    public void makeTrades(Trade trade){
        SoftAssert asert=new SoftAssert();
        Team team = new Team().withLogin("SIA_99_A629").withPassword("aqAwaEMl");//QA: SIA_99_A676/ 4AK4G8RD ; Prod: SIA_99_A629/ aqAwaEMl
        app.getGamesHelper().loginAsTeam(team);
        app.getGamesHelper().goToSubMenu("TRADE", "Enter a Trade");
        app.getGamesHelper().makeTrade(trade);
        app.getGamesHelper().confirmTrade(team, trade);
        asert.assertTrue(trade.isConfirmed());
        asert.assertAll();
    }

}
