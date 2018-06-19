package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlightSelectPage extends MainPage {

    public void verifyDate(String from, String to){
        WebElement checkDataFrom = driver.findElement(By.xpath("//form/table[1]//td[@class='title'][2]//font"));
        Assert.assertEquals(from, String.valueOf(checkDataFrom.getText()));

        WebElement checkDataTo = driver.findElement(By.xpath("//form/table[2]//td[@class='title'][2]//font"));
        Assert.assertEquals(to, String.valueOf(checkDataTo.getText()));
    }

    public void verifyDestination(String from, String to){

        WebElement checkDestinationFrom = driver.findElement(By.xpath("//form/table[1]//td[@class=\"title\"][1]/b/font"));
        Assert.assertEquals(from +" to " + to, String.valueOf(checkDestinationFrom.getText()));

        WebElement checkDestinationTo = driver.findElement(By.xpath("//form/table[2]//td[@class=\"title\"][1]/b/font"));
        Assert.assertEquals(to + " to " + from, String.valueOf(checkDestinationTo.getText()));
    }

    public void clickContinue(){
        driver.findElement(By.name("reserveFlights")).click();
    }
}
