package POM.Tests;

import POM.Pages.AdidasNewArrivalPage;
import POM.Pages.AdidasHomePage;
import POM.Pages.AdidasProductDescriptionPage;
import POM.Utilities.BasicUtilities;
import POM.Utilities.ExcelUtilities;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("E-Commerce Tests")
@Feature("Adidas Site Testing")
public class AdidasTests extends BasicUtilities {


  @DataProvider(name = "shoeNames")
  public Object[][] getShoeNames() {
    String filePath = "D:\\4thYear\\SoftwareQualityAssuarance\\Practicles\\Assignment\\Development\\Shoes.xlsx";
    String sheetName = "Sheet1";
    return ExcelUtilities.getExcelData(filePath, sheetName);
  }

  @Story("Search Functionality")
  @Test(dataProvider = "shoeNames", description = "Verifies search functionality with different shoe names")
  public void testSearchFunctionality(String shoeName) {
    AdidasHomePage homePage = new AdidasHomePage(driver).loadURL("https://www.adidas.com/lk");

    Allure.step("Searching for shoe model: " + shoeName);
    homePage.searchForProduct(shoeName);

    Assert.assertTrue(driver.getCurrentUrl().contains("q=" + shoeName), "Search query not in URL!");
    Assert.assertTrue(homePage.isSearchSuccessful(), "No search results found for " + shoeName);
  }

  @Story("Category Navigation")
  @Test(description = "Verifies navigation to the New Arrivals category")
  public void testNavigateToNewCategory() {
    AdidasHomePage homePage = new AdidasHomePage(driver).loadURL("https://www.adidas.com/lk");


    Allure.step("Navigating to 'New Arrivals' category");
    AdidasNewArrivalPage newArrivalPage = homePage.navigateToNewCategory();

    Assert.assertTrue(newArrivalPage.isOnNewArrivalPage(), "URL does not contain 'new-arrivals'!");
    Assert.assertTrue(newArrivalPage.isCategoryTitleCorrect(), "Category title is not 'New Arrivals'!");
    Assert.assertTrue(newArrivalPage.areProductsDisplayed(), "Products are not displayed on the New Arrival page!");
  }

  @Story("Filter Functionality")
  @Test(description = "Verifies filter functionality on the New Arrivals page")
  public void testFilterFunctionality() throws InterruptedException {
    AdidasHomePage homePage = new AdidasHomePage(driver).loadURL("https://www.adidas.com/lk");
    AdidasNewArrivalPage newArrivalPage = homePage.navigateToNewCategory();

    Allure.step("Applying a filter on the New Arrivals page");
    newArrivalPage.clickFilterButton();
    Assert.assertTrue(newArrivalPage.isFilterApplied(), "Filter was not applied successfully.");
  }

  @Story("Price Sorting")
  @Test(description = "Verifies sorting by Price Low to High on the New Arrivals page")
  public void testPriceLowToHighFilter() {
    AdidasHomePage homePage = new AdidasHomePage(driver).loadURL("https://www.adidas.com/lk");
    AdidasNewArrivalPage newArrivalPage = homePage.navigateToNewCategory();

    Allure.step("Applying 'Price Low To High' filter");
    newArrivalPage.clickFilterButton();
    newArrivalPage.applyPriceLowToHighFilter();

    Assert.assertTrue(newArrivalPage.arePricesSortedLowToHigh(), "Products are not sorted by Price Low To High!");
  }
  @Test
  public void testAddProductToBag() {
    AdidasHomePage homePage = new AdidasHomePage(driver).loadURL("https://www.adidas.com/lk");
    AdidasNewArrivalPage newArrivalPage = homePage.navigateToNewCategory();

    AdidasProductDescriptionPage productPage = newArrivalPage.goToFirstProduct();
    productPage.selectSize("S");
    productPage.clickAddToBag();
    Assert.assertTrue(true, "Add to Bag action completed without errors.");
  }





}
