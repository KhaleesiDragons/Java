
import PageObjects.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MyTest {

    MainPage mainPage=new MainPage();
    RegisterPage registerPage=new RegisterPage();
    FlightsFinderPage flightsPage=new FlightsFinderPage();
    FlightSelectPage flightSelectPage=new FlightSelectPage();
    BookFlightPage bookFlight=new BookFlightPage();
    FlightConfirmPage flightConfirmPage=new FlightConfirmPage();

    @BeforeSuite
    public void start() throws InterruptedException {
        mainPage.runDriver("src\\main\\resources\\chromedriver.exe");
        mainPage.openBrowser("http:newtours.demoaut.com");
        mainPage.clickRegister();
        registerPage.fillUserInformationForm("Test123", "Test123?");
    }


    @Test
    public void testA(){
        mainPage.clickFlights();
        flightsPage.selectDestination("Frankfurt", "San Francisco");
        flightsPage.selectMonth("June","June");
        flightsPage.selectDay("20","30");
        flightsPage.clickFindFlights();
        flightSelectPage.verifyDate("6/20/2018", "6/30/2018");
        flightSelectPage.verifyDestination("Frankfurt", "San Francisco");
        flightSelectPage.clickContinue();
        bookFlight.verifyDates("6/20/2018", "6/30/2018");
        bookFlight.verifyDestination("Frankfurt", "San Francisco");
        bookFlight.fillForm("TestName", "TestLastName", "123456");
        flightConfirmPage.verifyDestination("Frankfurt", "San Francisco");
        flightConfirmPage.verifyDate("6/20/2018", "6/30/2018");

    }

    @Test
    public void testB() throws InterruptedException {
        mainPage.clickFlights();
        flightsPage.selectDestination("Sydney", "Acapulco");
        flightsPage.selectMonth("July","July");
        flightsPage.selectDay("22","30");
        flightsPage.clickFindFlights();
        flightSelectPage.verifyDate("7/22/2018", "7/30/2018");
        flightSelectPage.verifyDestination("Sydney", "Acapulco");
        flightSelectPage.clickContinue();
        bookFlight.verifyDates("7/22/2018", "7/30/2018");
        bookFlight.verifyDestination("Sydney", "Acapulco");
        bookFlight.fillForm("TestName", "TestLastName", "123456");
        flightConfirmPage.verifyDestination("Sydney", "Acapulco");
        flightConfirmPage.verifyDate("7/22/2018", "7/30/2018");
    }

    @AfterSuite
    public void quit(){
        mainPage.quitDriver();
    }
}
