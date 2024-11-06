package POM.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
  private static BrowserFactory browserFactory;


  private final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

  private BrowserFactory() {}

  public static BrowserFactory getBrowserFactory() {
    if (browserFactory == null) {
      browserFactory = new BrowserFactory();
    }
    return browserFactory;
  }

  public WebDriver getDriver(String browserType) {
    if (threadLocalDriver.get() == null) {
      WebDriver driver;
      switch (browserType.toLowerCase()) {
        case "chrome":
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          break;
        case "firefox":
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
          break;
        case "edge":
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
          break;
        default:
          throw new RuntimeException("Unsupported browser type: " + browserType);
      }
      driver.manage().window().maximize();
      threadLocalDriver.set(driver); // Set the driver instance for this thread
    }
    return threadLocalDriver.get();
  }

  public void quitDriver() {
    WebDriver driver = threadLocalDriver.get();
    if (driver != null) {
      driver.quit();
      threadLocalDriver.remove();
    }
  }
}
