package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("Email"));
        driver.findElement(By.id("small-searchterms"));
    }

    @Test
    public void TC_02_Class() {
        // Class chỉ truyền vào 1 phần giá trị nếu có khoảng trắng
        // Lấy hết nếu không có khoảng trắng
        driver.findElement(By.className("login-button"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("Email"));
    }

    @Test
    public void TC_04_TagName() {
//        Tìm ra bao nhiêu thẻ HTML giống nhau
        int inputTagName = driver.findElements(By.tagName("input")).size();
        System.out.println(inputTagName);
    }

    @Test
    public void TC_05_LinkText() {
//        Dùng với đường link (Lấy tuyệt đối)
//        Lấy text trên UI
        driver.findElement(By.linkText("My account"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
//        Dùng với đường link (Lấy tương đối)
//        Lấy text trên UI
        driver.findElement(By.partialLinkText("account"));
    }

    @Test
    public void TC_07_Css() {
//        CSS với ID
        driver.findElement(By.cssSelector("input[id='Email']"));
        driver.findElement(By.cssSelector("input#Email"));
        driver.findElement(By.cssSelector("#Email"));
//        CSS với Class
        driver.findElement(By.cssSelector("button[class='button-1 register-button']"));
        driver.findElement(By.cssSelector("button.register-button"));
        driver.findElement(By.cssSelector(".register-button"));
        driver.findElement(By.cssSelector("button[class='button-1 login-button']"));
        driver.findElement(By.cssSelector("button.button-1.login-button"));

//        CSS với Name
        driver.findElement(By.cssSelector("input[name='Password']"));
//        CSS với TagName
        driver.findElements(By.cssSelector("input"));
//        CSS với LinkText
        driver.findElement(By.cssSelector("a[href='/customer/info']"));
//        CSS với Partial LinkText
        driver.findElement(By.cssSelector("a[href*='info']"));
    }

    @Test
    public void TC_08_Xpath() {
//        Xpath với ID
        driver.findElement(By.xpath("//input[@id='Email']"));
//        Xpath với Class
        driver.findElement(By.xpath("//button[@class='button-1 register-button']"));
        driver.findElement(By.xpath("//button[@class='button-1 login-button']"));
//        Xpath với Name
        driver.findElement(By.xpath("//input[@name='Password']"));
//        Xpath với TagName
        driver.findElements(By.xpath("//input"));
//        Xpath với LinkText
        driver.findElement(By.xpath("//a[@href='/customer/info']"));
        driver.findElement(By.xpath("//a[text()='My account']"));
//        Xpath với Partial LinkText
        driver.findElement(By.xpath("//a[contains(@href,'info')]"));
        driver.findElement(By.xpath("//a[contains(text(),'My account')]"));
    }

    @AfterClass
    public void afterClass() {

//        driver.quit();
    }
}
