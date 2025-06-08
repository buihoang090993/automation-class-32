package Webdriver;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_ElementCommands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }
    @Test
    public void TC_01_FireFox() {
        // Click element dạng button/checkbox/Radio/link/image...
        driver.findElement(By.cssSelector("")).click();
        // Nhập liệu vào element cho phép edil (Editable): Textbox/text area/Editable Dropdown
        driver.findElement(By.cssSelector("")).sendKeys("abc");
        // Xóa dữ liệu đã nhập
        driver.findElement(By.cssSelector("")).clear();
        // Tìm element cha => con
        driver.findElement(By.cssSelector("form#small-search-box-form")).findElement(By.cssSelector("input#small-searchterms"));
        // Kiểm tra 1 element là enable hay disable
        // enable
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isEnabled());
        // disable
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());
        // Kiểm tra element có thể nhìn thấy trên UI + kích thước (rộng + cao) lớn hơn 0
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
        // Kiểm tra element được chọn hay chưa (áp dụng cho checkbox/Radio/Dropdown)
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());
        // Lấy ra text của element (link/Header/Error message/success message) (text bao gồm cả element con)
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(), "abc");
        // Lấy ra giá trị của attribute
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("placeholder"), "abc");
        // Lấy ra giá trị của DOM property
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("value"), "abc");
        // Lấy ra giá trị của Css (front end)
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getCssValue("background-color"), "rgb(51, 153, 204)");
        // Lấy ra chiều rông + chiều cao của element
        Dimension loginButtonSize = driver.findElement(By.cssSelector("")).getSize();
        loginButtonSize.getHeight();
        loginButtonSize.getWidth();
        // Lấy ra vị trí của element trên màn hình
        Point loginButtonLocation = driver.findElement(By.cssSelector("")).getLocation();
        loginButtonLocation.getX();
        loginButtonLocation.getY();
        // Trả về cả size và location
        Rectangle loginButtonRect = driver.findElement(By.cssSelector("")).getRect();
        loginButtonRect.getHeight();
        loginButtonRect.getWidth();
        loginButtonRect.getX();
        loginButtonRect.getY();
        loginButtonRect.getDimension();
        loginButtonRect.getPoint();
        // Lấy ra tagName HTML của element
        driver.findElement(By.cssSelector("")).getTagName();
        // Dùng để xử lý shadow DOM
        driver.findElement(By.cssSelector("")).getShadowRoot();
        // Gửi 1 submit lên server
        // Chỉ áp dụng cho element nằm trong thẻ form
        driver.findElement(By.cssSelector("")).submit();
    }


}
