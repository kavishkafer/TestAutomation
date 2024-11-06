package POM.Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdidasHomePage extends BasePage {
  public AdidasHomePage(WebDriver webdriver){
    super(webdriver);
  }

  // Locate search input field
  @FindBy(xpath = "//input[@type='search' and @name='q']")
  private WebElement searchField;

  // Locate search button
  @FindBy(xpath = "//button[@name='search-button']")
  private WebElement searchButton;

  // Locator for consent modal accept button (Update based on actual button)
  @FindBy(xpath = "//button[@class='affirm btn btn-primary btn-inline']")
  private WebElement consentAcceptButton;
  @FindBy(xpath = "//div[contains(@class, 'product-tile')]")
  private List<WebElement> searchResults;

  @FindBy(xpath = "//a/span[text()='New']")
  private WebElement newCategoryLink;
  private static boolean isConsentAccepted = false;

  public void acceptConsentModal() {
    if (!isConsentAccepted) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      try {
        WebElement consentButton = wait.until(ExpectedConditions.visibilityOf(consentAcceptButton));
        consentButton.click();
        isConsentAccepted = true;  // Set flag to true after accepting consent
      } catch (Exception e) {
        System.out.println("Consent modal not displayed or already handled.");
      }
    }
  }


  public void searchForProduct(String productName) {
    acceptConsentModal();  // Ensure the modal is handled once per session
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(productName);
    wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
  }


  public boolean isSearchSuccessful() {
    return !searchResults.isEmpty();
  }

  public AdidasNewArrivalPage navigateToNewCategory() {
    acceptConsentModal();


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(newCategoryLink));
    newCategoryLink.click();


    wait.until(ExpectedConditions.urlContains("new-arrivals"));

    return new AdidasNewArrivalPage(driver);  // Return NewArrivalPage instance
  }
}


