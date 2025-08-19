package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Frame_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_WordPress() {
        driver.get("https://toidicodedao.com/");

        // Switch qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        System.out.println(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText());

        // Switch về page
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("div.content-sidebar input[class='search-field']")).sendKeys("puppeteer");
        driver.findElement(By.cssSelector("div.content-sidebar input[class='search-field']")).sendKeys(Keys.ENTER);
    }

    @Test
    public void TC_02_FormSite() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Interactive form loaded. Try filling out below.']")).isDisplayed());

        // Switch qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("West Dorm");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",
                driver.findElement(By.xpath("//label[text()='Female']/preceding-sibling::input")));

        // Switch về page
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("a[title='Get this form']")).click();
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @Test
    public void TC_03_HDFC() {
        driver.get("https://netbanking.hdfcbank.com");

        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("HSBC");
        driver.findElement(By.cssSelector("a.login-btn")).click();

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
