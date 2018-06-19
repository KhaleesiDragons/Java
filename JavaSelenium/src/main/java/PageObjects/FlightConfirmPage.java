package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FlightConfirmPage extends MainPage {
    List<WebElement> flightsInfo;

    public void getList(){
        flightsInfo = driver.findElements(By.xpath("//td[@class=\"frame_header_info\" and @valign=\"top\"]/font"));
    }

    public void verifyDestination(String from, String to){
        getList();
        Assert.assertTrue(flightsInfo.get(0).getText().startsWith(from + " to " + to));
        Assert.assertTrue(flightsInfo.get(1).getText().startsWith(to + " to " + from));
    }

    public void verifyDate(String from, String to){
        Assert.assertTrue(flightsInfo.get(0).getText().contains(from));
        Assert.assertTrue(flightsInfo.get(1).getText().contains(to));
    }
}
