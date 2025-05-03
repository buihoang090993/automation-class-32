package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Relative_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    @Test
    public void TC_01_Relative_Locator() {
        WebElement rememberMeText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(By.cssSelector("button.login-button"))
                .below(By.cssSelector("input.password"))
                .toRightOf(By.id("RememberMe"))
                .toLeftOf(By.xpath("//a[text()='Forgot password?']")));
        System.out.println(rememberMeText.getText());
    }

    @AfterClass
    public void afterClass() {

//        driver.quit();
    }
}
