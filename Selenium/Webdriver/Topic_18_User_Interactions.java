package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_User_Interactions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/");
        // Hover Icon Menu
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        // Hover Sách Giáo Khoa 2025
        actions.moveToElement(driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav')]//span[text()='Sách Giáo Khoa 2025']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        // Click vào Luyện Thi Môn Toán
        actions.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Luyện Thi Môn Toán']"))).perform();
        // Verify
        Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Toán']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
