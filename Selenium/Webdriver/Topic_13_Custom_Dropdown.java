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
    public void TC_01_OrangeHRM() throws InterruptedException {
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
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employment Status']/parent::div/following-sibling::div" +
                "//div[@class='oxd-select-text-input']")).getText(), "Full-Time Probation");
        // Chọn Past Employees Only trong Dropdown Include
        selectItemOrangeHRMDropdown("Include", "Past Employees Only");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Include']/parent::div/following-sibling::div" +
                "//div[@class='oxd-select-text-input']")).getText(), "Past Employees Only");
        // Chọn QA Engineer trong Dropdown Job Title
        selectItemOrangeHRMDropdown("Job Title", "QA Engineer");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div" +
                "//div[@class='oxd-select-text-input']")).getText(), "QA Engineer");
        // Chọn Human Resources trong Dropdown Sub Unit
        selectItemOrangeHRMDropdown("Sub Unit", "TechOps");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Sub Unit']/parent::div/following-sibling::div" +
                "//div[@class='oxd-select-text-input']")).getText(), "TechOps");
    }

    @Test
    public void TC_02_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        // Chọn Slow trong Select a speed
        selectItemDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Slow");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Slow");
        // Chọn some unknown file trong Select a file
        selectItemDropdown("//span[@id='files-button']", "//ul[@id='files-menu']/li/div", "Some unknown file");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(), "Some unknown file");
        //Chọn ui.JQuery.js trong Select a file
        selectItemDropdown("//span[@id='files-button']", "//ul[@id='files-menu']/li/div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");
        // Chọn 10 trong Select a number
        selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "10");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "10");
        // Chọn Prof. trong Select a title
        selectItemDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li/div", "Prof.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Prof.");
    }

    @Test
    public void TC_03_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'selection dropdown')]")).getText(), "Elliot Fu");
        selectItemDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'selection dropdown')]")).getText(), "Stevie Feliciano");
        selectItemDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'selection dropdown')]")).getText(), "Justen Kitsune");
    }

    @Test
    public void TC_04_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
        selectItemDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
    }

    @Test
    public void TC_05_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Aland Islands");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Aland Islands");
        selectItemEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Belgium");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Belgium");
    }

    @Test
    public void TC_06_FinPeace() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");
        selectItemEditableDropdown("//input[@id='job_id']",
                "//div[@id='job_id_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Công nghệ thông tin");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Nghề nghiệp']" +
                "/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Công nghệ thông tin");
        selectItemEditableDropdown("//input[@id='gender']",
                "//div[@id='gender_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Giới tính']" +
                "/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Nam");
        selectItemEditableDropdown("//input[@id='married_status']",
                "//div[@id='married_status_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Kết hôn, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Tình trạng hôn nhân']" +
                "/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Kết hôn, chưa có con");

    }

    public void selectItemEditableDropdown(String parentLocator, String childLocator, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentLocator)).clear();
        driver.findElement(By.xpath(parentLocator)).sendKeys(expectedItem);
        Thread.sleep(2000); //-- Comment đi thì không click được
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));

        for (WebElement item : allItems) {
            if(item.getText().equals(expectedItem)){
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    public void selectItemDropdown(String parentLocator, String childLocator, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentLocator)).click();
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
        for(WebElement item : allItems) {
            if(item.getText().equals(expectedItem)) {
                Thread.sleep(2000);
                item.click();
                break;
            }
        }
    }

    public void selectItemOrangeHRMDropdown(String locator, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath("//label[text()='"+ locator +"']/parent::div/following-sibling::div/div")).click();
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//label[text()='"+ locator + "']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-option')]/span")));

        for (WebElement item : allItems) {
            if(item.getText().equals(expectedItem)){
                item.click();
                Thread.sleep(2000);
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
