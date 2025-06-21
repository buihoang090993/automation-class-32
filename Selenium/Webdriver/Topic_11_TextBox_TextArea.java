package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_11_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, emailAddress, password, fullName;
    String userName, employeeID, passportNumber, passportComment;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        firstName = "McCathay";
        lastName = "John";
        fullName = firstName + " " + lastName;
        emailAddress = lastName + new Random().nextInt(999) + "@gmail.com";
        password = "McCathayJohnx99";
        userName = firstName + new Random().nextInt(999);
        passportNumber = "45445-5552-55897";
        passportComment = "Valid passport\nOk";
    }

    @Test
    public void TC_01_TechPanda() {
        driver.get("http://live.techpanda.org/");
        // Click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // Click Create An Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // Nhập thông tin
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        // Click register
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        System.out.println(contactInfo);
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Click edit
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getDomAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getDomAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getDomAttribute("value"), emailAddress);

        // Chuyển sang tab Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        // Click Samsung Galaxy
        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();
        // Click Add Your Review
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        // Điền đầy đủ thông tin
        driver.findElement(By.cssSelector("input[value='5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good application\nPretty easy to navigate.");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best phone");
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(fullName);
        // Click submit review
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Your review has been accepted for moderation.");

    }

    @Test
    public void TC_02_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Chờ icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear());
        // Mở PIM
        driver.findElement(By.xpath("//span[text()='PIM']//parent::a")).click();
        // Click Add Employee
        Assert.assertTrue(isLoadingIconDisappear());
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        // Nhập thông tin
        Assert.assertTrue(isLoadingIconDisappear());
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println(employeeID);
        driver.findElement(By.xpath("//p[text()='Create Login Details']//following-sibling::div//span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed();
        Assert.assertTrue(isLoadingIconDisappear());
        // Verify Personal Detail
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertEquals(driver.findElement(By.name("firstName")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getDomProperty("value"), employeeID);
        // Add Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed();
        Assert.assertTrue(isLoadingIconDisappear());
        // Click edit
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
                .getDomProperty("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
                .getDomProperty("value"), passportComment);
        // Log out
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        // Login lại
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        // Verify My info
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertEquals(driver.findElement(By.name("firstName")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getDomProperty("value"), employeeID);
        // Verify Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
                .getDomProperty("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
                .getDomProperty("value"), passportComment);
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
