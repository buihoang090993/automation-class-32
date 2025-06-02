package Webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Browser {
    WebDriver driver;

    @Test
    public void TC_01_FireFox() {
        driver = new FirefoxDriver();
        driver.quit();
    }

    @Test
    public void TC_01_Chrome() {
        driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    public void TC_01_Edge() {
        driver = new EdgeDriver();
        driver.quit();
    }

}
