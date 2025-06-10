package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Browser_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_URL() {
        driver.get("https://live.techpanda.org/");
        // Click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Verify
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        // Click Create an Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // Verify
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Title() {
        driver.get("https://live.techpanda.org/");
        // Click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Verify
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        // Click Create an Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // Verify
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate() {
        driver.get("https://live.techpanda.org/");
        // Click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Click Create an Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // Verify
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
        // Back lại trang login page
        driver.navigate().back();
        // Verify
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        // Forward tới trang register page
        driver.navigate().forward();
        // Verify
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("https://live.techpanda.org/");
        // Click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Verify
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        // Click Create an Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // Verify
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
