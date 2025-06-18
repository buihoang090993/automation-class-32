package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Element_Exercise_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Empty() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Nhập dữ liệu invalid email
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Kennedy");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("123@123@123");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("123@123@123");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
    }

    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Nhập dữ liệu invalid confirm email
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Kennedy");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("Kennedy@gmail");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Nhập dữ liệu invalid password
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Kennedy");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Nhập dữ liệu invalid confirm password
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Kennedy");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123457");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Click đăng ký
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Nhập dữ liệu invalid confirm password
        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Kennedy");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("Kennedy@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("123456");
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),
                "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
