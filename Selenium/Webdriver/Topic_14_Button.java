package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Login_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        // Click tab Đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        // Verify button Đăng nhập disable
        By loginButton = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        // Verify màu của button
        String loginButtonColorRGBA = driver.findElement(loginButton).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginButtonColorRGBA).asHex(), "#000000");
        // Nhập thông tin user + password
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0983006547");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        // Verify button Đăng nhập enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        // Verify màu của button
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
