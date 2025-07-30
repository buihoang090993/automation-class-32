package Webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_18_User_Interactions {
    WebDriver driver;
    Actions actions;
    String osName;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        osName = System.getProperty("os.name");

        jsExecutor = (JavascriptExecutor) driver;
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

    @Test
    public void TC_03_Click_And_Hold_Multiple() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems = driver.findElements(By.cssSelector("ol#selectable li"));
        // Click and hold từ 1 đến 12
        actions.clickAndHold(allItems.getFirst()).moveToElement(allItems.get(11)).release().perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        //Verify
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable li.ui-selected")).size(), 12);
    }

    @Test
    public void TC_04_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems = driver.findElements(By.cssSelector("ol#selectable li"));
        // Nhấn phím Ctrl
        Keys key = null;
        if(osName.contains("Windows")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        actions.keyDown(key).perform();

        // Click random 1 - 3 - 6 - 11
        actions.click(allItems.get(0))
                .click(allItems.get(2))
                .click(allItems.get(5))
                .click(allItems.get(10))
                .perform();
        // Nhả phím Ctrl
        actions.keyUp(key).perform();
        actions.pause(Duration.ofSeconds(2));
        //Verify
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable li.ui-selected")).size(), 4);
    }

    @Test
    public void TC_05_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
        // Double click
        actions.doubleClick(doubleClickButton).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_06_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        // Quit menu context chưa hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        // Click chuột phải
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        // Quit menu context hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        // Hover vào quit menu
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();
        // Quit menu sẽ có thêm trạng thái hover
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
        // Click vào quit
        driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
        // Accept alert
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.alertIsPresent()).accept();
        // Quit menu context chưa hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
