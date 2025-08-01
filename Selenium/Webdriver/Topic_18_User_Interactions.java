package Webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class Topic_18_User_Interactions {
    WebDriver driver;
    Actions actions;
    String osName;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    String jQueryFilePath;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        osName = System.getProperty("os.name");

        jsExecutor = (JavascriptExecutor) driver;

        jQueryFilePath = projectPath + "\\dragAndDrop\\dragAndDrop.js";
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
        if (osName.contains("Windows")) {
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

    @Test
    public void TC_07_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        // Drag and drop
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).perform();
        // Verify
        Assert.assertEquals(Color.fromString(sourceCircle.getCssValue("background-color")).asHex(), "#03a9f4");
        Assert.assertEquals(targetCircle.getText(), "You did great!");
    }

    @Test
    public void TC_08_Drag_And_Drop_HTML5_JS() throws IOException, InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        Thread.sleep(3000);
        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        String jQueryFileContent = getContentFile(jQueryFilePath);
        jQueryFileContent = jQueryFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExecutor.executeScript(jQueryFileContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        jsExecutor.executeScript(jQueryFileContent);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = StandardCharsets.UTF_8;
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @Test
    public void TC_09_Drag_And_Drop_HTML5_Robot() throws AWTException, InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        Thread.sleep(3000);

        String sourceXpath = "//div[@id='column-a']";
        String targetXpath = "//div[@id='column-b']";

        dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");

        dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
