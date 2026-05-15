# Selenium WebDriver Click-by-Click Documentation

## Project Information

Project name: TutorialNinja Selenium WebDriver E-commerce Test

Website under test: https://tutorialsninja.com/demo/

Testing type: Functional UI automation testing

Testing tool: Selenium WebDriver with Java

Build tool: Maven

Test framework: JUnit 5

Browser: Google Chrome

## Why this website was selected

TutorialNinja / Qafox OpenCart demo was selected because it includes the main e-commerce features required for the project:

- Register / Sign Up
- Login
- Search bar
- Wish List
- Shopping Cart
- Add to Cart buttons
- Product pages

The site is simple and suitable for practicing Selenium locators, WebElements, sendKeys, and click actions.

## Test Scope

The automation test covers:

1. Home page verification
2. Register new user
3. Logout
4. Login with created user
5. Product search
6. Add product to wishlist
7. Verify product exists in wishlist
8. Add product to cart
9. Verify product exists in cart
10. Change currency to Euro
11. Add product to compare list
12. Submit product review

## Main Locators Used

| Feature | Locator strategy | Locator |
|---|---|---|
| First name | id | input-firstname |
| Last name | id | input-lastname |
| Email | id | input-email |
| Telephone | id | input-telephone |
| Password | id | input-password |
| Confirm Password | id | input-confirm |
| Privacy Policy checkbox | name | agree |
| Register button | cssSelector | input.btn-primary[value='Continue'] |
| Login button | cssSelector | input.btn-primary[value='Login'] |
| Search box | name | search |
| Search button | cssSelector | #search button |
| Wishlist button | cssSelector | button[onclick*='wishlist.add'] |
| Add to Cart button | id | button-cart |
| Quantity | id | input-quantity |
| Currency menu | cssSelector | button.btn-link.dropdown-toggle |
| Euro currency | name | EUR |
| Compare button | cssSelector | button[onclick*='compare.add'] |
| Review tab | cssSelector | a[href='#tab-review'] |
| Review name | id | input-name |
| Review text | id | input-review |
| Review rating | cssSelector | input[name='rating'][value='5'] |
| Submit review | id | button-review |

## Test Case 1: Register New User

Test case ID: TC_REG_001

Purpose: Verify that a new user can register successfully.

Preconditions:

- Chrome is installed.
- Internet connection is available.
- User email must be unique.

Test data:

- First name: Akram
- Last name: QA
- Email: generated dynamically using timestamp
- Telephone: 01000000000
- Password: Akram@12345

Click-by-click procedure:

1. Open Chrome browser.
2. Navigate to https://tutorialsninja.com/demo/index.php?route=account/register.
3. Locate First Name field by id input-firstname.
4. Type first name using sendKeys.
5. Locate Last Name field by id input-lastname.
6. Type last name using sendKeys.
7. Locate Email field by id input-email.
8. Type generated email using sendKeys.
9. Locate Telephone field by id input-telephone.
10. Type telephone using sendKeys.
11. Locate Password field by id input-password.
12. Type password using sendKeys.
13. Locate Confirm Password field by id input-confirm.
14. Type same password using sendKeys.
15. Locate Newsletter Yes radio button.
16. Click Newsletter Yes.
17. Locate Privacy Policy checkbox by name agree.
18. Click checkbox.
19. Locate Continue button.
20. Click Continue.

Expected result:

- Account registration succeeds.
- Success page contains Your Account Has Been Created.

## Test Case 2: Login

Test case ID: TC_LOGIN_001

Purpose: Verify that the created user can login.

Preconditions:

- User account is already created.

Click-by-click procedure:

1. Navigate to login page.
2. Locate email field by id input-email.
3. Type registered email using sendKeys.
4. Locate password field by id input-password.
5. Type password using sendKeys.
6. Locate Login button.
7. Click Login.

Expected result:

- User is redirected to My Account page.
- Page contains My Account.

## Test Case 3: Search Product

Test case ID: TC_SEARCH_001

Purpose: Verify that search bar returns product results.

Test data:

- Search keyword: iPhone

Click-by-click procedure:

1. Open home page.
2. Locate search box by name search.
3. Type iPhone using sendKeys.
4. Locate search button using cssSelector #search button.
5. Click search button.

Expected result:

- Search results page opens.
- Page contains iPhone.

## Test Case 4: Add Product to Wishlist

Test case ID: TC_WISHLIST_001

Purpose: Verify that logged-in user can add product to wishlist.

Preconditions:

- User is logged in.

Click-by-click procedure:

1. Open iPhone product page.
2. Locate wishlist button using cssSelector button[onclick*='wishlist.add'].
3. Click wishlist button.
4. Wait for success alert.
5. Open wishlist page.
6. Verify page contains iPhone.

Expected result:

- Product is added to wishlist.
- Wishlist page contains iPhone.

## Test Case 5: Add Product to Cart

Test case ID: TC_CART_001

Purpose: Verify that product can be added to shopping cart.

Click-by-click procedure:

1. Open iPhone product page.
2. Locate quantity field by id input-quantity.
3. Clear quantity field.
4. Type 1 using sendKeys.
5. Locate Add to Cart button by id button-cart.
6. Click Add to Cart.
7. Wait for success alert.
8. Open shopping cart page.
9. Verify page contains iPhone.

Expected result:

- Product is added to cart.
- Cart page contains iPhone.

## Extra Test Case 6: Change Currency

Test case ID: TC_EXTRA_001

Purpose: Verify that user can change currency.

Click-by-click procedure:

1. Open home page.
2. Locate currency dropdown.
3. Click currency dropdown.
4. Locate Euro button.
5. Click Euro.

Expected result:

- Prices display the Euro symbol.

## Extra Test Case 7: Product Comparison

Test case ID: TC_EXTRA_002

Purpose: Verify that product can be added to comparison list.

Click-by-click procedure:

1. Open product page.
2. Locate Compare button using cssSelector button[onclick*='compare.add'].
3. Click Compare.
4. Wait for success alert.
5. Open product comparison page.
6. Verify page contains product name.

Expected result:

- Product comparison page contains iPhone.

## Extra Test Case 8: Product Review

Test case ID: TC_EXTRA_003

Purpose: Verify review form interaction.

Click-by-click procedure:

1. Open product page.
2. Open Reviews tab.
3. Locate Your Name field.
4. Type reviewer name using sendKeys.
5. Locate Your Review field.
6. Type review text using sendKeys.
7. Select rating 5.
8. Click Continue.

Expected result:

- Review confirmation or review message appears.

## Notes About Selenium Implementation

The implementation uses explicit waits with WebDriverWait. This is better than Thread.sleep because the test waits for a real condition, such as element visibility, clickability, or page text existence.

The test also uses scrollIntoView before clicking elements to reduce click interception issues.
