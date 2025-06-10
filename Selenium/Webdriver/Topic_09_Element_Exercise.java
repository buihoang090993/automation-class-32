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

public class Topic_09_Element_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed_Enable_Selected() {
        driver.get("https://www.fahasa.com/customer/account/create");
        // Element hiển thị: có thể nhìn thấy + có kích thước cụ thể
        // Mong đợi 1 element hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone_otp")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_password")).isDisplayed());
        // Mong đợi 1 element không hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_username")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_password")).isDisplayed());
        // Element enable: có thể thao tác được (không bị read-only = Disable)
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#register_phone_otp")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#register_password")).isEnabled());
        // Element selected: Đã được chọn
        driver.get("https://live.techpanda.org/index.php/customer/account/create/");
        Assert.assertTrue(driver.findElement(By.cssSelector("input#is_subscribed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#is_subscribed")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#is_subscribed")).isSelected());
    }

    @Test
    public void TC_02_Mailchimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("hoang@gmail.com");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        // Lower Case
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Upper Case
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("button#create-account-enabled")));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Special
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@!$%");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // 8-char
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("test@gmail.vn");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Full
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Test12@gmail.vn");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleep(3);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }

    private void sleep(long timeInSecond) {
        try {
            Thread.sleep(Duration.ofSeconds(timeInSecond));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_03_Login_Empty() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Bỏ trông email + password
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();
        // Click login
        driver.findElement(By.cssSelector("button#send2")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
