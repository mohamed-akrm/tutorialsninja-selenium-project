# TutorialNinja Selenium WebDriver Project

This Maven project tests a small e-commerce user journey on TutorialNinja / Qafox OpenCart demo.

## Covered requirements

- Register / Sign Up
- Login Form
- Search Bar
- Add to Wishlist
- Add to Cart
- Extra coverage: change currency, product comparison, product review

## Tech stack

- Java
- Selenium WebDriver
- WebDriverManager
- JUnit 5
- Maven

## How to run in IntelliJ

1. Extract the ZIP file.
2. Open the folder `tutorialsninja-selenium-project` in IntelliJ IDEA.
3. Wait until Maven finishes downloading dependencies.
4. Open `src/test/java/org/example/TutorialNinjaClickByClickTest.java`.
5. Run the test method `fullEcommerceUserJourney()`.

## Main test file

`src/test/java/org/example/TutorialNinjaClickByClickTest.java`

## Notes

- The test creates a new unique account every run using a timestamp-based email.
- The test uses explicit waits (`WebDriverWait`), not fixed `Thread.sleep()` waits.
- Chrome must be installed on the machine.
