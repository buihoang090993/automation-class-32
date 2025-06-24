package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_OrangeHRM() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Chờ icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear());
        // Mở PIM
        driver.findElement(By.xpath("//span[text()='PIM']//parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Chọn Full-Time Probation trong Dropdown Employment Status
        selectItemOrangeHRMDropdown("Employment Status", "Full-Time Probation");
        // Chọn Past Employees Only trong Dropdown Include
        selectItemOrangeHRMDropdown("Include", "Past Employees Only");
        // Chọn QA Engineer trong Dropdown Job Title
        selectItemOrangeHRMDropdown("Job Title", "QA Engineer");
        // Chọn Human Resources trong Dropdown Sub Unit
        selectItemOrangeHRMDropdown("Sub Unit", "TechOps");
    }

    public void selectItemOrangeHRMDropdown(String locator, String expectedItem) {
        driver.findElement(By.xpath("//label[text()='"+ locator +"']/parent::div/following-sibling::div/div")).click();
        List<WebElement> allItems = driver.findElements(By.xpath("//label[text()='"+ locator +
                "']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-option')]/span"));

        for (WebElement item : allItems) {
            if(item.getText().contains(expectedItem)){
                item.click();
                break;
            }
        }
    }

    private boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
