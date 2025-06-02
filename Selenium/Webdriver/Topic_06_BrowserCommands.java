package Webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_06_BrowserCommands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() {
        // Các hàm tương tác với Browser thông qua biến driver
        // driver là biến đại diện cho Selenium Driver

        // Các hàm sẽ tương tác với Element thông qua findElement
        // findElement đại diện cho Selenium WebElement

        // Mở ra URL bất kỳ
        driver.get("https://demo.nopcommerce.com/");
        // Đóng toàn bộ tab
        driver.quit();
        // Đóng tab đang đứng hiện tại
        driver.close();
        // Tìm 1 element
        // Nếu có nhiều element => Trả về element đầu tiên
        driver.findElement(By.cssSelector(""));
        // Tìm nhiều element => Trả về kiểu list
        List<WebElement> element = driver.findElements(By.cssSelector(""));
        // Trả về URL của page hiện tại
        driver.getCurrentUrl();
        // Trả về toàn bộ source HTML của page hiện tại
        driver.getPageSource();
        // Trả về toàn bộ title của page hiện tại
        driver.getTitle();
        // Trả về ID của tab/window hiện tại
        driver.getWindowHandle();
        // Trả về ID của toàn bộ tab/window
        driver.getWindowHandles();
        // Mở rộng của sổ trình duyệt
        driver.manage().window().maximize();
        // Thu nhỏ cửa sổ về dưới Taskbar
        driver.manage().window().minimize();
        // Full màn hình (full viền) trình duyệt
        driver.manage().window().fullscreen();
        // Set kích thước trình duyệt: width: chiều rộng, height: chiều cao
        driver.manage().window().setSize(new Dimension(30, 90));
        // Lấy ra kích thước trình duyệt
        driver.manage().window().getSize();
        // Set vị trí trình duyệt trên màn hình máy tính
        driver.manage().window().setPosition(new Point(10, 10));
        // Lấy ra vị trí trình duyệt trên màn hình
        driver.manage().window().getPosition();
        // Set time out
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Lấy ra giá trị set time out
        driver.manage().timeouts().getImplicitWaitTimeout();
        // Set time out (dùng cho JSExecutor)
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        // Lấy ra giá trị set time out
        driver.manage().timeouts().getScriptTimeout();
        // Set time out cho thời gian load page
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        // Lấy ra giá trị set time out
        driver.manage().timeouts().getPageLoadTimeout();
        // Lấy cookies
        driver.manage().getCookies();
        // Set cookies
        driver.manage().addCookie(new Cookie("name", "value"));
        // Xóa cookies
        driver.manage().deleteCookie(new Cookie("name", "value"));
        // Lấy log của selenium (Cách này ít dùng, thường dùng log4j)
        driver.manage().logs();
        // Điều hướng đến page khác theo URL
        driver.navigate().to("");
        // Quay lại sang trước
        driver.navigate().back();
        // Forward
        driver.navigate().forward();
        // F5
        driver.navigate().refresh();
        // Switch to (dùng cho alert, Window/Tab, Frame/iFrame)
        driver.switchTo().alert();
        driver.switchTo().window("ID");
        driver.switchTo().frame("ID");


    }
}
