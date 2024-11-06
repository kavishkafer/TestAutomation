package POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
  protected WebDriver driver = null;

  public BasePage(WebDriver driver){
    this.driver = driver;
  }

  public AdidasHomePage loadURL(String url){
    driver.get(url);
    return PageFactory.initElements(driver, AdidasHomePage.class);

  }

}
