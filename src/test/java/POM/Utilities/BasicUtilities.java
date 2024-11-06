package POM.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BasicUtilities {
  protected BrowserFactory browserFactory;
  protected WebDriver driver;

  @Parameters("browserType")
  @BeforeTest
  public void initializeTheBrowser(@Optional("chrome") String browserType) {
    browserFactory = BrowserFactory.getBrowserFactory();
    driver = browserFactory.getDriver(browserType);
    driver.get("https://www.adidas.com/lk");
  }

  @AfterTest
  public void exitBrowser() {
    browserFactory.quitDriver();
  }
}
