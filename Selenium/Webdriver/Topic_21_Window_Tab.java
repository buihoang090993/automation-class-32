package Webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class Topic_21_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lấy ra ID của tab hiện tại mà driver đang đứng ở đó
        String githubWindowID = driver.getWindowHandle();

        WebElement scrollElement = driver.findElement(By.xpath("//legend[text()='Table']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollElement);
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        switchToWindowByID(githubWindowID);

        driver.findElement(By.cssSelector("textarea[aria-label='Tìm kiếm']")).sendKeys("Automation Testing");
        driver.findElement(By.cssSelector("textarea[aria-label='Tìm kiếm']")).sendKeys(Keys.ENTER);

        String googleWindowID = driver.getWindowHandle();

        switchToWindowByID(googleWindowID);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);
        switchToWindowByTitle("Facebook");

        driver.findElement(By.cssSelector("input#email")).sendKeys("hoang@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("hoang@gmail.com");

        switchToWindowByTitle("Selenium WebDriver");
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

        closeAllWindowWithoutParent(githubWindowID);
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSecond(2);

        switchToWindowByTitle("Products Comparison List");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSecond(2);

        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        String homeWindowID = driver.getWindowHandle();

        sleepInSecond(2);
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
        sleepInSecond(2);

        switchToWindowByTitle("Login");

        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-loginID']")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-password']")).getText(), "This field is required");

        closeAllWindowWithoutParent(homeWindowID);

        String keyWord = "Selenium";
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(keyWord);
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'uk')]/preceding-sibling::div[@class='di-title']/span/span")).getText(),
                keyWord.toLowerCase());
    }

    @Test
    public void TC_04_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");

        String homePageWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        sleepInSecond(2);
        String loginPageWindowID = driver.getWindowHandle();

        switchToWindowByID(loginPageWindowID);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='DCE Login Portal']")).isDisplayed());

        closeAllWindowWithoutParent(homePageWindowID);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.activescreen")).isDisplayed());

        driver.findElement(By.cssSelector("button[class*='button--cancel']")).click();
        sleepInSecond(2);

        String keyWord = "Data Science: An Artificial Ecosystem";
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys(keyWord);
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Full Term");

        driver.findElement(By.cssSelector("button#search-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(), keyWord);
    }

    @Test
    public void TC_05_Selenium_4x() {
        driver.get("https://demo.nopcommerce.com/");
        // Mở new Window
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://admin-demo.nopcommerce.com/");
        // Mở new Tab
        driver.switchTo().newWindow(WindowType.TAB).get("https://live.techpanda.org/");
    }

    public void sleepInSecond(long timeInSecond) throws InterruptedException {
        Thread.sleep(timeInSecond * 1000);
    }

    // Dùng khi có 2 tab/window
    public void switchToWindowByID(String windowID) throws InterruptedException {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }

        sleepInSecond(2);
    }

    // Dùng khi có trên 2 tab/window
    public void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for(String id : allWindowIDs) {
            driver.switchTo().window(id);
            sleepInSecond(1);
            if(Objects.requireNonNull(driver.getTitle()).contains(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(String windowID) throws InterruptedException {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                sleepInSecond(2);
                driver.close();
            }
        }

        driver.switchTo().window(windowID);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
