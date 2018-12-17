package appmanager;

import models.Team;
import models.Trade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Arrays;

public class GamesHelper extends HelperBase {
    public GamesHelper(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }


    public void loginAsTeam(Team team) {
        //wd.navigate().to("http://ww3.smgny.org/login.html");
        wd.navigate().to("https://www.stockmarketgame.org/login.html");
        wd.findElement(By.cssSelector("input[name=ACCOUNTNO]")).sendKeys(team.getLogin());
        wd.findElement(By.cssSelector("input[name=USER_PIN]")).sendKeys(team.getPassword());
        wd.findElement(By.cssSelector("input[value=Login]")).click();
        wait.until(ExpectedConditions.visibilityOf(wd.findElement
                (By.xpath(String.format("//span[.='%s']", team.getLogin())))));
    }

    public void goToSubMenu(String menu, String sub_Menu){
        hoverOverElement(getMenuElement(menu));
        wait.until(ExpectedConditions.visibilityOf(getMenuElement(menu)
                .findElement(By.xpath(String.format(".//a[.='%s']", sub_Menu)))));
        getMenuElement(menu).findElement(By.xpath(String.format(".//a[.='%s']", sub_Menu))).click();
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("a#aMutualFundTrade"))));
    }

    private WebElement getMenuElement(String menu) {
        return wd.findElement(By.xpath(String.format("//a[@class = 'parent'][text() = '%s']/..",menu)));
    }

    public void makeTrade(Trade trade) {
        openTradePage(trade.getEqtype());
        selectOrder(trade);
        enterSymbol(trade.getSymbol());
        enterAmount(trade.getAmount());
        if(trade.getEqtype().equals("Stock")){
            selectType(trade.getType());
            if (trade.getType().equals("Limit")){
                enterLimitPrice(trade.getPrice());
            }
        }
        goToPreview(trade.getEqtype());

    }


    private void goToPreview(String eqtype) {
        if (eqtype.equals("Stock")){
            wd.findElement(By.cssSelector("input#btnSend")).click();
        } else if (eqtype.equals("MutualFund") || eqtype.equals("Bond")){
            wd.findElement(By.cssSelector("input#btnPreviewTrade")).click();
        }
    }

    private void selectOrder(Trade trade) {
        String[] allowed_values = {"Buy", "Sell", "ShortCell", "Shortcover"};
        if(Arrays.stream(allowed_values).anyMatch(trade.getOrder()::equals)){
            if (trade.getEqtype().equals("Stock")){
                selectOrderStock(trade.getOrder());
            } else if (trade.getEqtype().equals("MutualFund") || trade.getEqtype().equals("Bond")){
                selectOrderFundBond(trade.getOrder());
            } else {
                System.out.println("Incorrect EQT Type" + trade.getEqtype());
            }
        } else if (trade.getOrder().length() > 0){
            System.out.println("Invalid Order Type " + trade.getOrder());
        } else {
            System.out.println("Trade Order Type is empty");
        }
    }

    private void selectOrderFundBond(String order) {
        wd.findElement(By.cssSelector(String.format("input[value=%s]",order.toLowerCase()))).click();
    }

    private void enterLimitPrice(String limit_price) {
        if(limit_price.length() > 0){
            wd.findElement(By.cssSelector("input#LimitPrice")).sendKeys(limit_price);
        } else {
            System.out.println("Empty Limit Price");
        }
    }

    private void selectType(String type) {
        if(type.length() > 0){
            Select select_type = new Select(wd.findElement(By.cssSelector("select#OrderType")));
            select_type.selectByVisibleText(type);
        } else {
            System.out.println("Empty Type");
        }
    }

    private void enterAmount(String amount) {
        if(amount.length() > 0){
            wd.findElement(By.cssSelector("input#BuySellAmt")).sendKeys(amount);
        } else {
            System.out.println("Empty Amount");
        }
    }

    private void openTradePage(String eqtype) {
        switch (eqtype){
            case "Stock":
                wd.findElement(By.cssSelector("a#aStockTrade")).click();
                break;
            case "MutualFund":
                wd.findElement(By.cssSelector("a#aMutualFundTrade")).click();
                break;
            case "Bond":
                wd.findElement(By.cssSelector("a#aBondTrade")).click();
                break;
            default:
                System.out.println("Wrong EQT type " + eqtype);
        }
    }

    private void enterSymbol(String symbol) {
        if(symbol.length() > 0){
            wd.findElement(By.cssSelector("input#SymbolName")).sendKeys(symbol);
        } else {
            System.out.println("Empty Symbol");
        }
    }

    private void selectOrderStock(String order) {
        wd.findElement(By.cssSelector(String.format("input#rb%s",order))).click();
    }
    
    public void confirmTrade(Team team, Trade trade) {
        wd.findElement(By.cssSelector("input#TradePassword")).sendKeys(team.getPassword());
        wd.findElement(By.cssSelector("input#btnConfirmTrade")).click();
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div#divFinalTradeResponse"))));
        String response = wd.findElement(By.cssSelector("div#divFinalTradeResponse")).getText();
        if(response.contains("Confirmed")){
            trade.withConfirmed(true);
            trade.withId(extractRegEx("Confirmed: ([A-Z]{3}-[0-9]*)",response));
        } else {
            trade.withMsg(response);
            trade.withConfirmed(false);
        }
    }

    public void checkTradeConfirmed(Trade trade, SoftAssert asert) {
        if(trade.getMsg().contains("system is unavailable during the following times")) {
            System.out.println("System is unavailable now");
        } else if(trade.getMsg().contains("5 minutes")) {
            System.out.println("Same symbol in 5 minutes");
        }  else {
            asert.assertTrue(trade.isConfirmed());
        }
    }
}
