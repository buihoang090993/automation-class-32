package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Popup {
    WebDriver driver;
    int SHORT_TIMEOUT = 5;
    int LONG_TIMEOUT = 15;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(LONG_TIMEOUT));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_In_HTML() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        Thread.sleep(2000);
        // 1 - Nếu như popup có hiển thị thì close đi => Click vào FAQ link
        // 2 - Nếu như popup không hiển thị => Click vào FAQ link
        By popup = By.cssSelector("div[class='pop-container']");

        if(driver.findElement(popup).isDisplayed()) {
            driver.findElement(By.cssSelector("div.close")).click();
            Thread.sleep(2000);
        }

        Assert.assertFalse(driver.findElement(popup).isDisplayed());

        driver.findElement(By.xpath("//a[text()='FAQ']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='KMPlayer FAQ']")).isDisplayed());
    }

    @Test
    public void TC_02_Not_In_HTML() throws InterruptedException {
        driver.get("https://tienganhcomaiphuong.vn/");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);
        // Verify popup hiển thị
        By popup = By.cssSelector("div[class*='MuiDialog-container']");

        Assert.assertTrue(driver.findElement(popup).isDisplayed());

        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("hoangbeo");
        driver.findElement(By.cssSelector("input[placeholder='Mật khẩu']")).sendKeys("123456");
        driver.findElement(By.cssSelector("div.auth-form button[class*='dialog-button']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("button[class*='close-btn']")).click();
        Thread.sleep(2000);
        // Verify popup không hiển thị
        Assert.assertEquals(driver.findElements(popup).size(), 0);
    }

    @Test
    public void TC_03_Random_Popup() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(2000);

        By popup = By.cssSelector("div#VIP_BUNDLE");
        // Nếu như có popup thì close đi => Click vào đăng nhập
        // Nếu không có popup thì click vào đăng nhập

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
        List<WebElement> popupElements = driver.findElements(popup);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(LONG_TIMEOUT));
        if(!popupElements.isEmpty() && popupElements.getFirst().isDisplayed()) {
            driver.findElement(By.cssSelector("picture[class='webpimg-container']>img[alt='close-icon']")).click();
            Thread.sleep(2000);
        }

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);

        // popup fix cứng
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class*='ReactModal__Content']")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());

        driver.findElement(By.cssSelector("div[class*='ReactModal__Content'] img.close-img")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
        List<WebElement> loginPopup = driver.findElements(By.cssSelector("div[class*='ReactModal__Content'] img.close-img"));
        Assert.assertEquals(loginPopup.size(), 0);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
