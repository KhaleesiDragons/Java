package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    static WebDriver driver=null;

    public void runDriver(String chromeDriver){
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        driver = new ChromeDriver();
    }

    public  void openBrowser(String myUrl){
        driver.get(myUrl);
    }

    public void clickRegister(){
        driver.findElement(By.xpath("//a[text()=\"REGISTER\"]")).click();
    }

    public void clickFlights(){
        driver.findElement(By.xpath("//a[text() ='Flights']")).click();
    }

    public void closeBrowser(){
        driver.close();
    }

    public void quitDriver(){
        if (driver != null)
            driver.quit();
    }

}
