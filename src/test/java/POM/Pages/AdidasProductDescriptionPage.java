package POM.Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdidasProductDescriptionPage extends BasePage {

  public AdidasProductDescriptionPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  // Locators
  @FindBy(xpath = "//label[@class='radio-input_option']//span[@class='size-value']")
  private List<WebElement> sizeOptions;  // List of all available sizes

  @FindBy(xpath = "//button[contains(@class, 'add-to-cart')]")
  private WebElement addToBagButton;

  @FindBy(xpath = "//div[@class='card-body']//a[@class='product_link']//span")
  private List<WebElement> addedProductNames;  // List of product names in the cart

  // Method to select a specific size (e.g., "S")
  public void selectSize(String desiredSize) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    for (WebElement sizeOption : sizeOptions) {
      if (sizeOption.getText().equalsIgnoreCase(desiredSize)) {
        wait.until(ExpectedConditions.elementToBeClickable(sizeOption)).click();
        break;
      }
    }

    // Add a small wait after selecting the size
    try {
      Thread.sleep(2000);  // Wait for 2 seconds
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  // Method to add item to the bag
  public void addToBag() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(addToBagButton))
        .click();
  }

  // Method to verify that the correct item is added to the bag
  public boolean isProductInBag(String corduroyTartanWideLegPants) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wait until any overlay or loading spinner disappears
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("underlay")));

    // Attempt to find the product in the bag
    try {
      WebElement productInBag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']//a[@class='product_link']//span[text()='Corduroy Tartan Wide Leg Pants']")));
      return productInBag != null; // Product was found in the bag
    } catch (TimeoutException e) {
      System.out.println("Product not found in the bag within the timeout.");
      return false;
    }
  }


  public void clickAddToBag() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Scroll to and click the "Add to Bag" button
    wait.until(ExpectedConditions.visibilityOf(addToBagButton));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToBagButton);

    // Attempt to click the "Add to Bag" button
    try {
      wait.until(ExpectedConditions.elementToBeClickable(addToBagButton)).click();
      System.out.println("Add to Bag button clicked successfully.");
    } catch (ElementClickInterceptedException e) {
      System.out.println("Element intercepted, retrying with JavaScript click.");
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToBagButton);
    }
  }





}
