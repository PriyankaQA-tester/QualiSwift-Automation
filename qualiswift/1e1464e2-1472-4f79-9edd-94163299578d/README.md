# The Internet Homepage Selenium Java Tests

This test suite uses Selenium WebDriver with Java to validate the homepage of https://the-internet.herokuapp.com/.

## Setup Instructions

1. **Java and Maven:** Ensure Java (JDK 8 or higher) and Maven are installed.

2. **WebDriver Binaries:** Install a WebDriver binary (e.g., ChromeDriver for Chrome). Include the WebDriver executable in your system PATH.

3. **Dependencies:** Use Maven to manage dependencies (Selenium Java).

4. **Running Tests:** Integrate these static test methods in the execution framework which provides `QSWebDriverRunner.Context` with a configured `driver`.

   - The `Context` wraps a `RemoteWebDriver driver` instance accessible as `c.driver`.

5. **Environment:** Requires internet access to reach https://the-internet.herokuapp.com/.

6. **Timeouts:** Each test waits up to 10 seconds for critical elements.

## Notes

- Tests assume the home page structure and selectors as per current site.
- Tests check visibility, text content, and filtering behavior.
- Sleeping briefly after filter input to allow UI update.

## Package

Tests are packaged in `QualiswiftTests.java` as static test methods named test_001 to test_009 matching test case indexes.

## No Credentials

This site is public, no credentials are required.

