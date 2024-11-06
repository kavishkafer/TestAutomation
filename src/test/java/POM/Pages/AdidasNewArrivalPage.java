package POM.Pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdidasNewArrivalPage extends BasePage {
  @FindBy(xpath = "//div[contains(@class, 'product-tile')]")
  private List<WebElement> productTiles;

  // Locator for the category title "New Arrivals"
  @FindBy(xpath = "//span[@class='categoryName']")
  private WebElement categoryTitle;

  @FindBy(xpath = "//div[@class='filter-button-wrapper d-none d-md-block']//button[@class='btn filter-results col-12 m-0']")
  private WebElement filterButton;


  @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div/div[1]")
  private WebElement sortByDropdown;


  @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/ul/li[3]")
  private WebElement priceLowToHighOption;



  @FindBy(xpath ="//a[contains(@class, 'apply-sticky-btn') and contains(text(), 'Apply')]" )
  private WebElement applyButton;

  @FindBy(xpath = "//div[@class='product']//span[@class='sales']//span[@class='value']")
  private List<WebElement> productPrices;
  @FindBy(xpath = "//button[contains(text(), 'Accept')]")
  private WebElement popUpButton;

  @FindBy(xpath = "//div[@class='col-md-3 products-item col-6' and @data-index='1.0']")
  private WebElement firstProduct;
  public AdidasNewArrivalPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }


  public boolean areProductsDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfAllElements(productTiles));
    return !productTiles.isEmpty();
  }

  public boolean isOnNewArrivalPage() {
    return driver.getCurrentUrl().contains("new-arrivals");
  }


  public boolean isCategoryTitleCorrect() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(categoryTitle));
    return categoryTitle.getText().equals("New Arrivals");
  }

  public void clickFilterButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement button = wait.until(ExpectedConditions.elementToBeClickable(filterButton));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
  }


  public boolean isFilterApplied() {
    return driver.getCurrentUrl().contains("filter") || !productTiles.isEmpty();
  }
  public void applyPriceLowToHighFilter() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.elementToBeClickable(sortByDropdown)).click();
    wait.until(ExpectedConditions.visibilityOf(priceLowToHighOption));
    if (priceLowToHighOption.isDisplayed()) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", priceLowToHighOption);
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", priceLowToHighOption);
    } else {
      System.out.println("Price Low To High option is not visible.");
    }

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    wait.until(ExpectedConditions.elementToBeClickable(applyButton));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyButton);


    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


   public boolean arePricesSortedLowToHigh() {
     handlePopUp();
    // Extract prices as a list of doubles
    List<Double> prices = productPrices.stream()
        .map(e -> e.getText().replaceAll("[^\\d.]", "")) // Remove non-numeric characters
        .filter(text -> !text.isEmpty()) // Filter out empty strings if any
        .map(Double::parseDouble) // Convert to Double
        .collect(Collectors.toList());
    System.out.println(prices);

    // Check if prices are sorted in ascending order
    for (int i = 0; i < prices.size() - 1; i++) {
      if (prices.get(i) > prices.get(i + 1)) {
        return false; // If any price is greater than the next, return false
      }
    }
    return true; // If all prices are in ascending order, return true
  }
  private void handlePopUp() {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      wait.until(ExpectedConditions.elementToBeClickable(popUpButton));
      popUpButton.click();
    } catch (Exception e) {
      System.out.println("No pop-up to handle.");
    }
  }
  public AdidasProductDescriptionPage goToFirstProduct() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(firstProduct))
        .click();
    return new AdidasProductDescriptionPage(driver);
  }
  }
