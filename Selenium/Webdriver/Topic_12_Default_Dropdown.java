package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Facebook() throws InterruptedException {
        driver.get("https://www.facebook.com/r.php?entry_point=login");

        select = new Select(driver.findElement(By.cssSelector("select#day")));
        // Ít dùng, lấy giá trị theo index của option
        select.selectByIndex(6);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "7");
        Thread.sleep(2000);
        // Ít dùng, lấy giá trị theo value của option
        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByValue("10");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 10");
        Thread.sleep(2000);
        // Nên dùng, lấy giá trị theo text của option
        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("1993");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1993");
    }

    @Test
    public void TC_02_Rode() throws InterruptedException {
        driver.get("https://www.rode.com/wheretobuy");

        // Chọn VietNam
        select = new Select(driver.findElement(By.cssSelector("select#country")));
        select.selectByVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
        // Lọc theo HO CHI MINH
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        // Click search
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(2000);
        // Verify số lượng dealer
        List<WebElement> dealerNumber = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div"));
        Assert.assertEquals(dealerNumber.size(), 16);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
