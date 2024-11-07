# Adidas E-commerce Automation Project

This project automates testing for key functionalities on the Adidas e-commerce site, including searching for products, filtering results, navigating categories, and adding items to the bag. The project is designed with the Page Object Model (POM) architecture, using TestNG for test orchestration, Selenium WebDriver for UI automation, and Allure for reporting.

---

## 1. Project Overview

### Objective
The objective is to automate regression and functional test cases for core workflows on the Adidas site, improving testing speed, accuracy, and early detection of issues.

### Tools Used
- **Selenium WebDriver**: For browser-based UI automation
- **TestNG**: Manages test orchestration, parameterization, parallel execution, and data-driven testing
- **Allure**: Generates detailed test reports
- **WebDriverManager**: Manages browser drivers for simplified test setup
- **Java**: Programming language for automation scripts

### Scope
This project covers:
- Functional Testing: Core functionality like search, category navigation, filtering, and adding items to the cart.
- Cross-Browser Testing: Ensuring UI consistency across major browsers.
- Regression Testing: Validating stability when new changes are applied.

---

## 2. Project Structure


### Key Components
1. **Test Classes** (POM.Tests): Contains test methods covering search, navigation, filtering, and add-to-cart scenarios.
2. **Page Classes** (POM.Pages): Each page has its own class representing its UI elements and interactions.
3. **Utilities** (POM.Utilities): Support classes like `BrowserFactory`, `BasicUtilities`, and `ExcelUtilities` for environment setup and data handling.

---

## 3. Setup and Prerequisites

### Prerequisites
- **Java 17** or later
- **Maven** for dependency management
- **Allure** for report generation
- **Google Chrome** (or another browser configured in `BrowserFactory`)

### Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/kavishkafer/TestAutomation.git
    ```

2. **Install Dependencies**:
    Ensure Maven is installed, then run:
    ```bash
    mvn clean install
    ```

3. **Configure Allure** (if not installed):
    - [Download and install Allure](https://docs.qameta.io/allure/#_installing_a_commandline)
    - Ensure Allure is in your system path.

4. **Set Up Test Data**:
    - Ensure the `Shoes.xlsx` test data file is in the specified directory.
    - Update paths as needed in `ExcelUtilities.java`.

---

## 4. Running Tests

### Run Tests Using Maven
use IDE like intellij or eclipse for better user experience


