package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Custom_Checkbox_Radio {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Ubuntu() {
        driver.get("https://login.ubuntu.com/");
        // Cách 1: Click thẻ trên, verify thẻ input
        driver.findElement(By.cssSelector("label.new-user")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());
        // Cách 2: Dùng JS (khuyên dùng)
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("label[for='id_accept_tos']")));
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected());
    }

    @Test
    public void TC_02_Google() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.cssSelector("div[data-value='Cần Thơ']");
        driver.findElement(canThoRadio).click();
        Assert.assertEquals(driver.findElement(canThoRadio).getDomAttribute("aria-checked"), "true");

        By quangNamCheckbox = By.cssSelector("div[data-answer-value='Quảng Nam']");
        By quangBinhCheckbox = By.cssSelector("div[data-answer-value='Quảng Bình']");
        driver.findElement(quangNamCheckbox).click();
        driver.findElement(quangBinhCheckbox).click();
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getDomAttribute("aria-checked"), "true");
        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
