package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // setup chrome driver
        WebDriverManager.chromedriver().setup();

        // open chrome browser
        WebDriver driver = new ChromeDriver();

        // maximize browser window
        driver.manage().window().maximize();

        // create unique email for registration
        String email = "akram.qa." + System.currentTimeMillis() + "@gmail.com";

        // -------------------------
        // Sign Up Scenario
        // -------------------------

        // open register page
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
        Thread.sleep(3000);

        // fill register form
        driver.findElement(By.id("input-firstname")).sendKeys("Akram");
        Thread.sleep(1000);

        driver.findElement(By.id("input-lastname")).sendKeys("QA");
        Thread.sleep(1000);

        driver.findElement(By.id("input-email")).sendKeys(email);
        Thread.sleep(1000);

        driver.findElement(By.id("input-telephone")).sendKeys("01000000000");
        Thread.sleep(1000);

        driver.findElement(By.id("input-password")).sendKeys("Akram12345");
        Thread.sleep(1000);

        driver.findElement(By.id("input-confirm")).sendKeys("Akram12345");
        Thread.sleep(1000);

        // click privacy policy checkbox
        driver.findElement(By.name("agree")).click();
        Thread.sleep(1000);

        // click continue button
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        Thread.sleep(3000);

        // verify register
        if (driver.getPageSource().contains("Your Account Has Been Created")) {
            System.out.println("Register Passed");
        } else {
            System.out.println("Register Failed");
        }

        // click continue after register
        driver.findElement(By.linkText("Continue")).click();
        Thread.sleep(3000);

        // -------------------------
        // Logout Scenario
        // -------------------------

        // click My Account
        driver.findElement(By.linkText("My Account")).click();
        Thread.sleep(2000);

        // click Logout
        driver.findElement(By.linkText("Logout")).click();
        Thread.sleep(3000);

        // click Continue after logout
        driver.findElement(By.linkText("Continue")).click();
        Thread.sleep(3000);

        // -------------------------
        // Login Scenario
        // -------------------------

        // open login page
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        Thread.sleep(3000);

        // fill login form
        driver.findElement(By.id("input-email")).sendKeys(email);
        Thread.sleep(1000);

        driver.findElement(By.id("input-password")).sendKeys("Akram12345");
        Thread.sleep(1000);

        // click login button
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        Thread.sleep(3000);

        // verify login
        if (driver.getPageSource().contains("My Account")) {
            System.out.println("Login Passed");
        } else {
            System.out.println("Login Failed");
        }

        // -------------------------
        // Search Scenario
        // -------------------------

        // open home page
        driver.get("https://tutorialsninja.com/demo/");
        Thread.sleep(3000);

        // write product name in search box
        driver.findElement(By.name("search")).sendKeys("iPhone");
        Thread.sleep(1000);

        // click search button
        driver.findElement(By.cssSelector("#search button")).click();
        Thread.sleep(3000);

        // verify search result
        if (driver.getPageSource().contains("iPhone")) {
            System.out.println("Search Passed");
        } else {
            System.out.println("Search Failed");
        }

        // -------------------------
        // Add to Wishlist Scenario
        // -------------------------

        // open product page
        driver.get("https://tutorialsninja.com/demo/index.php?route=product/product&product_id=40");
        Thread.sleep(3000);

        // click add to wishlist button
        driver.findElement(By.cssSelector("button[onclick*='wishlist.add']")).click();
        Thread.sleep(3000);

        // open wishlist page
        driver.findElement(By.partialLinkText("Wish List")).click();
        Thread.sleep(3000);

        // verify wishlist
        if (driver.getPageSource().contains("iPhone")) {
            System.out.println("Wishlist Passed");
        } else {
            System.out.println("Wishlist Failed");
        }

        // -------------------------
        // Add to Cart Scenario
        // -------------------------

        // open product page again
        driver.get("https://tutorialsninja.com/demo/index.php?route=product/product&product_id=40");
        Thread.sleep(3000);

        // locate quantity input
        WebElement quantity = driver.findElement(By.id("input-quantity"));

        // clear old quantity value
        quantity.clear();
        Thread.sleep(1000);

        // type new quantity
        quantity.sendKeys("1");
        Thread.sleep(1000);

        // click add to cart button
        driver.findElement(By.id("button-cart")).click();
        Thread.sleep(3000);

        // open shopping cart
        driver.findElement(By.partialLinkText("Shopping Cart")).click();
        Thread.sleep(3000);

        // verify cart
        if (driver.getPageSource().contains("iPhone")) {
            System.out.println("Cart Passed");
        } else {
            System.out.println("Cart Failed");
        }

        // -------------------------
        // Extra Scenario: Change Currency
        // -------------------------

        // open home page
        driver.get("https://tutorialsninja.com/demo/");
        Thread.sleep(3000);

        // click currency dropdown
        driver.findElement(By.cssSelector("button.btn-link.dropdown-toggle")).click();
        Thread.sleep(2000);

        // choose Euro
        driver.findElement(By.name("EUR")).click();
        Thread.sleep(3000);

        // verify currency changed
        if (driver.getPageSource().contains("€")) {
            System.out.println("Currency Change Passed");
        } else {
            System.out.println("Currency Change Failed");
        }

        // close browser
        driver.quit();
    }
}