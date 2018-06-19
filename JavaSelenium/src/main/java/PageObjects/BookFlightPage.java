package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookFlightPage extends MainPage {
    public void fillForm(String name, String last, String number){
        driver.findElement(By.name("passFirst0")).sendKeys("TestName");
        driver.findElement(By.name("passLast0")).sendKeys("TestLastName");
        driver.findElement(By.name("creditnumber")).sendKeys("123456");
        driver.findElement(By.name("buyFlights")) .click();
    }

    public void verifyDates(String from, String to){
        WebElement checkDatesFrom = driver.findElement(By.xpath("//td[@class=\"frame_header_info\" and @align=\"right\"]//font"));
        Assert.assertEquals(from, String.valueOf(checkDatesFrom.getText()));

        WebElement checkDatesTo = driver.findElement(By.xpath("//td[@class=\"data_center_mono\" and @align=\"right\"]//font"));
        Assert.assertEquals(to, String.valueOf(checkDatesTo.getText()));
    }

    public void verifyDestination(String from, String to){

        WebElement checkDestinationFrom = driver.findElement(By.xpath("//td[@class=\"frame_header_info\"][1]/b/font"));
        Assert.assertEquals(from +" to " + to, String.valueOf(checkDestinationFrom.getText()));

        WebElement checkDestinationTo = driver.findElement(By.xpath("//td[@class=\"data_left\"][1]/b/font"));
        Assert.assertEquals(to + " to " + from, String.valueOf(checkDestinationTo.getText()));
    }
}
