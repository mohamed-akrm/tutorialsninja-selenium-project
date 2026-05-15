package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main {

    private static final String BASE_URL = "https://tutorialsninja.com/demo/";
    private static final String PRODUCT_NAME = "iPhone";
    private static final String PRODUCT_URL = BASE_URL + "index.php?route=product/product&product_id=40";

    private WebDriver driver;
    private WebDriverWait wait;

    private final String firstName = "Akram";
    private final String lastName = "QA";
    private final String email = "akram.qa." + System.currentTimeMillis() + "@gmail.com";
    private final String telephone = "01000000000";
    private final String password = "Akram@12345";

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Full Click-by-Click E-commerce Journey: Register, Login, Search, Wishlist, Cart, and Extras")
    void fullEcommerceUserJourney() {
        openHomePageAndVerifyMainElements();
        registerNewUser();
        logoutIfLoggedIn();
        loginWithCreatedUser();
        searchForProductAndVerify(PRODUCT_NAME);
        addProductToWishlistAndVerify();
        addProductToCartAndVerify();
        changeCurrencyToEuroAndVerify();
        addProductToCompareAndVerify();
        writeProductReviewAndVerify();
    }

    private void openHomePageAndVerifyMainElements() {
        driver.get(BASE_URL);
        waitPageContains("Featured");
        assertTrue(pageText().contains("My Account"), "Home page should contain My Account menu");
        assertTrue(pageText().contains("Wish List"), "Home page should contain Wish List link");
        assertTrue(pageText().contains("Shopping Cart"), "Home page should contain Shopping Cart link");
        assertTrue(pageText().contains("MacBook"), "Home page should contain featured products");
    }

    private void registerNewUser() {
        driver.get(BASE_URL + "index.php?route=account/register");

        type(By.id("input-firstname"), firstName);
        type(By.id("input-lastname"), lastName);
        type(By.id("input-email"), email);
        type(By.id("input-telephone"), telephone);
        type(By.id("input-password"), password);
        type(By.id("input-confirm"), password);

        click(By.cssSelector("input[name='newsletter'][value='1']"));
        click(By.name("agree"));
        click(By.cssSelector("input.btn-primary[value='Continue']"));

        wait.until(driver -> driver.getCurrentUrl().contains("account/success")
                || pageText().contains("Your Account Has Been Created")
                || pageText().contains("Warning"));

        String body = pageText();
        assertTrue(body.contains("Your Account Has Been Created") || driver.getCurrentUrl().contains("account/success"),
                "Registration should finish successfully. Page text: " + body);
    }

    private void logoutIfLoggedIn() {
        driver.get(BASE_URL + "index.php?route=account/logout");
        wait.until(driver -> pageText().contains("Account Logout") || pageText().contains("Register") || pageText().contains("Login"));
    }

    private void loginWithCreatedUser() {
        driver.get(BASE_URL + "index.php?route=account/login");

        type(By.id("input-email"), email);
        type(By.id("input-password"), password);
        click(By.cssSelector("input.btn-primary[value='Login']"));

        wait.until(driver -> driver.getCurrentUrl().contains("account/account")
                || pageText().contains("My Account")
                || pageText().contains("Warning"));

        String body = pageText();
        assertTrue(body.contains("My Account") && !body.contains("Warning: No match"),
                "Login should finish successfully. Page text: " + body);
    }

    private void searchForProductAndVerify(String keyword) {
        driver.get(BASE_URL);
        type(By.name("search"), keyword);
        click(By.cssSelector("#search button"));

        wait.until(driver -> driver.getCurrentUrl().contains("product/search")
                && pageText().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)));

        assertTrue(pageText().contains(keyword), "Search results should contain the searched product: " + keyword);
    }

    private void addProductToWishlistAndVerify() {
        driver.get(PRODUCT_URL);
        waitPageContains(PRODUCT_NAME);

        click(By.cssSelector("button[onclick*='wishlist.add']"));
        waitForAlertText("wish list");

        driver.get(BASE_URL + "index.php?route=account/wishlist");
        waitPageContains("My Wish List");
        assertTrue(pageText().contains(PRODUCT_NAME), "Wishlist should contain product: " + PRODUCT_NAME);
    }

    private void addProductToCartAndVerify() {
        driver.get(PRODUCT_URL);
        waitPageContains(PRODUCT_NAME);

        WebElement quantity = waitVisible(By.id("input-quantity"));
        quantity.clear();
        quantity.sendKeys("1");

        click(By.id("button-cart"));
        waitForAlertText("shopping cart");

        driver.get(BASE_URL + "index.php?route=checkout/cart");
        waitPageContains("Shopping Cart");
        assertTrue(pageText().contains(PRODUCT_NAME), "Shopping cart should contain product: " + PRODUCT_NAME);
    }

    private void changeCurrencyToEuroAndVerify() {
        driver.get(BASE_URL);
        click(By.cssSelector("button.btn-link.dropdown-toggle"));
        click(By.name("EUR"));
        waitPageContains("€");
        assertTrue(pageText().contains("€"), "Currency should be changed to Euro");
    }

    private void addProductToCompareAndVerify() {
        driver.get(PRODUCT_URL);
        waitPageContains(PRODUCT_NAME);

        click(By.cssSelector("button[onclick*='compare.add']"));
        waitForAlertText("product comparison");

        driver.get(BASE_URL + "index.php?route=product/compare");
        waitPageContains("Product Comparison");
        assertTrue(pageText().contains(PRODUCT_NAME), "Product comparison should contain product: " + PRODUCT_NAME);
    }

    private void writeProductReviewAndVerify() {
        driver.get(PRODUCT_URL);
        waitPageContains(PRODUCT_NAME);

        click(By.cssSelector("a[href='#tab-review']"));
        type(By.id("input-name"), firstName + " " + lastName);
        type(By.id("input-review"), "This is an automation review written by Selenium WebDriver for a QA class project.");
        click(By.cssSelector("input[name='rating'][value='5']"));
        click(By.id("button-review"));

        waitForAlertText("review");
        assertTrue(pageText().toLowerCase(Locale.ROOT).contains("review"), "Review message should appear");
    }

    private void type(By locator, String value) {
        WebElement element = waitVisible(locator);
        scrollTo(element);
        element.clear();
        element.sendKeys(value);
    }

    private void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            scrollTo(element);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (TimeoutException | ElementClickInterceptedException e) {
            throw new AssertionError("Could not click locator: " + locator
                    + "\nCurrent URL: " + driver.getCurrentUrl()
                    + "\nVisible page text:\n" + pageText(), e);
        }
    }

    private WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitPageContains(String expectedText) {
        wait.until(driver -> pageText().toLowerCase(Locale.ROOT).contains(expectedText.toLowerCase(Locale.ROOT)));
    }

    private void waitForAlertText(String expectedText) {
        wait.until(driver -> !driver.findElements(By.cssSelector(".alert, .alert-success, .alert-danger")).isEmpty()
                && pageText().toLowerCase(Locale.ROOT).contains(expectedText.toLowerCase(Locale.ROOT)));
    }

    private String pageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
