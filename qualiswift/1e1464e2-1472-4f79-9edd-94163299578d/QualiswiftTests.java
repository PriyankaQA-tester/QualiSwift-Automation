import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QualiswiftTests {

    private static final String BASE_URL = "https://the-internet.herokuapp.com/";

    public static void test_001(QSWebDriverRunner.Context c) throws Exception {
        // 1. Open https://the-internet.herokuapp.com/. 2. Wait for the page to load.
        c.navigate("");

        // Wait for main content element h1 heading visible to confirm page load
        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
    }

    public static void test_002(QSWebDriverRunner.Context c) throws Exception {
        // Preconditions: homepage open
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));
        // Wait for header visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("header")));

        WebElement header = c.driver.findElement(By.tagName("header"));
        // Check 'The Internet' branding - assuming h1 inside header
        WebElement branding = header.findElement(By.tagName("h1"));

        // Check 'powered by Sauce Labs' text visible somewhere in header
        String headerText = header.getText();

        if (!branding.isDisplayed() || !branding.getText().contains("The Internet")) {
            throw new AssertionError("Branding 'The Internet' not visible or incorrect.");
        }

        if (!headerText.contains("powered by Sauce Labs")) {
            throw new AssertionError("'powered by Sauce Labs' text not found in header.");
        }
    }

    public static void test_003(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        String text = heading.getText().trim();
        if (!"Welcome to the-internet".equals(text)) {
            throw new AssertionError("Heading text mismatch: expected 'Welcome to the-internet', found '" + text + "'");
        }
    }

    public static void test_004(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));
        // The 'Available Examples' heading is the first h2 element after main content
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));

        String text = heading.getText().trim();
        if (!"Available Examples".equals(text)) {
            throw new AssertionError("Examples heading text mismatch: expected 'Available Examples', found '" + text + "'");
        }
    }

    public static void test_005(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));

        // Label for filter input is <label for='full-text-search'>Filter examples:</label>
        WebElement filterLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='full-text-search']")));

        // Filter examples input
        WebElement filterInput = c.driver.findElement(By.id("full-text-search"));

        if (!filterLabel.isDisplayed()) {
            throw new AssertionError("Filter examples label not visible.");
        }

        if (!filterInput.isDisplayed() || !filterInput.isEnabled()) {
            throw new AssertionError("Filter input not visible or not usable.");
        }
    }

    public static void test_006(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));

        WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("full-text-search")));
        filterInput.clear();
        filterInput.sendKeys("Dynamic Content");

        Thread.sleep(500); // wait for filter effect

        // Check matching 'Dynamic Content' example is shown
        List<WebElement> examples = c.driver.findElements(By.cssSelector("#content ul li a"));

        boolean foundDynamicContent = false;
        for (WebElement example : examples) {
            if (example.isDisplayed()) {
                String text = example.getText();
                if (text.equals("Dynamic Content")) {
                    foundDynamicContent = true;
                } else {
                    if (text.toLowerCase().contains("dynamic content") == false) {
                        throw new AssertionError("Non-matching example visible after filtering: '" + text + "'");
                    }
                }
            }
        }

        if (!foundDynamicContent) {
            throw new AssertionError("Matching example 'Dynamic Content' not visible after filtering.");
        }
    }

    public static void test_007(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));

        WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("full-text-search")));
        filterInput.clear();
        filterInput.sendKeys("Dynamic");

        Thread.sleep(500); // wait for filter effect

        List<WebElement> examples = c.driver.findElements(By.cssSelector("#content ul li a"));
        List<String> visibleExampleTexts = new ArrayList<>();

        for (WebElement example : examples) {
            if (example.isDisplayed()) {
                String text = example.getText();
                visibleExampleTexts.add(text);
                if (!text.contains("Dynamic")) {
                    throw new AssertionError("Example visible that does not contain 'Dynamic': " + text);
                }
            }
        }

        if (visibleExampleTexts.isEmpty()) {
            throw new AssertionError("No examples visible after filtering with 'Dynamic'.");
        }
    }

    public static void test_008(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));

        WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("full-text-search")));
        filterInput.clear();
        filterInput.sendKeys("xyz123notfound");

        Thread.sleep(500); // wait for filter effect

        List<WebElement> examples = c.driver.findElements(By.cssSelector("#content ul li a"));

        boolean anyVisible = false;
        for (WebElement example : examples) {
            if (example.isDisplayed()) {
                anyVisible = true;
                break;
            }
        }

        if (anyVisible) {
            throw new AssertionError("Examples visible when filter should match none.");
        }

        // Verify page stability - no error text on body
        WebElement body = c.driver.findElement(By.tagName("body"));
        String bodyText = body.getText().toLowerCase();

        if (bodyText.contains("error")) {
            throw new AssertionError("Page contains error text after filtering with no matches.");
        }
    }

    public static void test_009(QSWebDriverRunner.Context c) throws Exception {
        c.navigate("");

        WebDriverWait wait = new WebDriverWait(c.driver, Duration.ofSeconds(10));

        List<WebElement> allExamplesElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#content ul li a")));
        List<String> allExamplesBefore = allExamplesElements.stream().filter(WebElement::isDisplayed).map(WebElement::getText).collect(Collectors.toList());

        if (allExamplesBefore.isEmpty()) {
            throw new AssertionError("No example links found initially.");
        }

        WebElement filterInput = c.driver.findElement(By.id("full-text-search"));
        filterInput.clear();
        filterInput.sendKeys("Dynamic");

        Thread.sleep(500); // wait for filtering

        List<WebElement> filteredElements = c.driver.findElements(By.cssSelector("#content ul li a"));
        List<String> filteredExamples = new ArrayList<>();
        for (WebElement el : filteredElements) {
            if (el.isDisplayed()) {
                filteredExamples.add(el.getText());
            }
        }

        if (filteredExamples.size() >= allExamplesBefore.size()) {
            throw new AssertionError("Filtered examples count not less than initial total.");
        }

        // Clear filter
        filterInput.clear();

        Thread.sleep(500); // wait for list to restore

        List<WebElement> allExamplesElementsAfter = c.driver.findElements(By.cssSelector("#content ul li a"));
        List<String> allExamplesAfter = new ArrayList<>();
        for (WebElement el : allExamplesElementsAfter) {
            if (el.isDisplayed()) {
                allExamplesAfter.add(el.getText());
            }
        }

        if (allExamplesAfter.size() != allExamplesBefore.size()) {
            throw new AssertionError("After clearing filter, examples count mismatch: before=" + allExamplesBefore.size() + ", after=" + allExamplesAfter.size());
        }

        // Verify both lists contain the same elements ignoring order
        List<String> beforeSorted = new ArrayList<>(allExamplesBefore);
        List<String> afterSorted = new ArrayList<>(allExamplesAfter);

        beforeSorted.sort(String::compareTo);
        afterSorted.sort(String::compareTo);

        if (!beforeSorted.equals(afterSorted)) {
            throw new AssertionError("Example lists before and after clearing filter differ.");
        }
    }

}