package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Home_Shop() {
        driver.get("https://shop.polymer-project.org/");

        WebElement shopAppShadowHost = driver.findElement(By.cssSelector("shop-app"));
        SearchContext shopAppShadowRoot = shopAppShadowHost.getShadowRoot();

        WebElement shopHomeShadowHost = shopAppShadowRoot.findElement(By.cssSelector("shop-home"));
        SearchContext shopHomeShadowRoot = shopHomeShadowHost.getShadowRoot();

        shopHomeShadowRoot.findElement(By.cssSelector("a[href*='mens_outerwear']~shop-button")).click();
    }

    @Test
    public void TC_02_Nested() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        // Driver đang thao tác với DOM bên ngoài chưa có vào shadow DOM
        Assert.assertEquals(driver.findElement(By.cssSelector("a[href='scroll.html']")).getText(), "scroll.html");
        Assert.assertEquals(driver.findElements(By.cssSelector("a")).size(), 1);

        // Thao tác với shadow DOM đầu tiên
        WebElement firstShadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span.info")).getText(), "some text");
        Assert.assertEquals(firstShadowRoot.findElements(By.cssSelector("a")).size(), 1);
        Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("a[href='scroll.html']")).getText(), "nested scroll.html");

        // Thao tác với Shadow DON thứ 2
        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();

        Assert.assertEquals(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }

    @Test
    public void TC_03_Book_App() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);

        WebElement bookAppShadowHost = driver.findElement(By.cssSelector("book-app"));
        SearchContext bookAppShadowRoot = bookAppShadowHost.getShadowRoot();

        bookAppShadowRoot.findElement(By.cssSelector("book-input-decorator>input#input")).sendKeys("Harry Potter");

        WebElement bookDecoratorShadowHost = bookAppShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        SearchContext bookDecoratorShadowRoot = bookDecoratorShadowHost.getShadowRoot();
        bookDecoratorShadowRoot.findElement(By.cssSelector("div.icon")).click();

        Thread.sleep(3000);

        WebElement bookExploreShadowHost = bookAppShadowRoot.findElement(By.cssSelector("book-explore"));
        SearchContext bookExploreShadowRoot = bookExploreShadowHost.getShadowRoot();

        List<WebElement> bookItems = bookExploreShadowRoot.findElements(By.cssSelector("section>ul.books>li>book-item"));
        System.out.println(bookItems.size());
        for (WebElement bookItem : bookItems) {
            SearchContext bookItemShadowRoot = bookItem.getShadowRoot();
            System.out.println(bookItemShadowRoot.findElement(By.cssSelector("div.title-container>h2.title")).getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
