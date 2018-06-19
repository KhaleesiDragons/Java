package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FlightsFinderPage extends MainPage{

    public void selectDestination(String from, String to){
        this.select(from, "fromPort");
        this.select(to,"toPort" );
    }

    public void selectMonth(String from, String to){
        this.select(from, "fromMonth");
        this.select(to,"toMonth" );
    }

    public void selectDay(String from, String to){
        this.select(from, "fromDay");
        this.select(to,"toDay" );
    }


    public void clickFindFlights(){
        WebElement submit=driver.findElement(By.xpath("//input[@name='findFlights']"));
        submit.click();
    }

    private void select(String text, String elementName)
    {
        WebElement selectFrom = driver.findElement(By.name(elementName));
        List<WebElement> options = selectFrom.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if(text.equals(option.getText()))
                option.click();
        }
    }


}
