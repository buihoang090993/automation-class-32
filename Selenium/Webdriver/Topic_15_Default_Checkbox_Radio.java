package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Default_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        By leatherTrimCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input");
        By towbarPreparationCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input");

        // Click chọn
        if(!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }

        // Kiểm tra đã chọn
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
        // Disable + Selected
        Assert.assertFalse(driver.findElement(leatherTrimCheckbox).isEnabled());
        Assert.assertTrue(driver.findElement(leatherTrimCheckbox).isSelected());
        // Disable + De-selected
        Assert.assertFalse(driver.findElement(towbarPreparationCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(towbarPreparationCheckbox).isSelected());

        // Click bỏ chọn
        if(driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By petro147Radio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        // Scroll
        if(!driver.findElement(petro147Radio).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));
            driver.findElement(petro147Radio).click();
        }

        Assert.assertTrue(driver.findElement(petro147Radio).isSelected());
    }

    @Test
    public void TC_02_Angular() {
        driver.get("https://material.angular.dev/components/radio/examples");
        By summerRadio = By.cssSelector("input[value='Summer']");
        if(!driver.findElement(summerRadio).isSelected()) {
            driver.findElement(summerRadio).click();
        }
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

        driver.get("https://material.angular.dev/components/checkbox/examples");
        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        if(!driver.findElement(checkedCheckbox).isSelected()) {
            driver.findElement(checkedCheckbox).click();
        }

        if(!driver.findElement(indeterminateCheckbox).isSelected()) {
            driver.findElement(indeterminateCheckbox).click();
        }

        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_03_All_Element() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//label[contains(text(),'Have you ever had')]")));
        List<WebElement> checkboxes = driver.findElements(By.xpath("//label[contains(text(),'Have you ever had')]/following-sibling::div//input"));
        // Click checkbox
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // Verify
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
