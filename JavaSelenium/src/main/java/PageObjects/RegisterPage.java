package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage extends MainPage{

    public void fillUserInformationForm(String user_name, String password) throws InterruptedException {
        WebElement username = super.driver.findElement(By.id("email"));
        Thread.sleep(500);
        username.sendKeys(user_name);

        WebElement pas = super.driver.findElement(By.xpath("//input[@name='password']"));
        pas.sendKeys(password);

        WebElement confirmpas = super.driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        confirmpas.sendKeys(password);

        WebElement button=super.driver.findElement(By.xpath("//input[@name='register']"));
        button.click();
    }
}
