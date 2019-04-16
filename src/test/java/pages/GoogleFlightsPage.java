package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;
import static org.testng.Assert.fail;
import static org.testng.internal.Utils.log;

public class GoogleFlightsPage {

    private static final long TIMEOUT = TimeUnit.SECONDS.toSeconds(60); // in seconds


    public void verifyGoogleFlightsSearchIsWorking(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement flightsHomepage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@title='Flights'])[2]")));
        System.out.println(flightsHomepage.toString() + "is displayed");

        // To Airport Selection
        WebElement toCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-flt-ve='destination_airport'])[8]")));
        toCity.click();
        WebElement toCityPopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Where to?']")));
        toCityPopup.clear();
        pasteFromClipboard(toCityPopup, "Vancouver");
        WebElement toCityAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//floating-action-button[@title='Select multiple airports']")));
        toCityAdd.click();
        WebElement toCityAirportSelection = wait.until(ExpectedConditions.elementToBeClickable(By.id("sbse0")));
        toCityAirportSelection.click();
        WebElement toCityConfirm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//floating-action-button[starts-with(@class,'jIIwapdBllK__confirm-button')]")));
        toCityConfirm.click();
        System.out.println("Vancouver Airport is selected as destination airport");

        // Select Travel Dates
        WebElement fromDateModal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target']/span)[1]")));
        js.executeScript("arguments[0].click();", fromDateModal);
        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Departure date']")));
        pasteFromClipboard(fromDate, "Mon, May 13");
        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Return date']")));
        pasteFromClipboard(toDate, "Fri, May 17");
        WebElement selectDates = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//g-raised-button[@data-flt-ve='done']")));
        js.executeScript("arguments[0].click();", selectDates);

        // Click Search button

        WebElement searchFlightsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//floating-action-button[starts-with(@class,'gws-flights-form__search-button')]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", searchFlightsButton);

        // Verify Search Results

        WebElement bestDepartingFlightsSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='gws-flights-results__best-flights']")));
        verifyObjectDisplayed(bestDepartingFlightsSection);

        WebElement otherDepartingFlightsSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='gws-flights-results__other_flights_heading']")));
        verifyObjectDisplayed(otherDepartingFlightsSection);

        WebElement chooseFirstCheapestDepartingFlight = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='gws-flights-results__collapsed-itinerary gws-flights-results__itinerary'])[1]")));
        js.executeScript("arguments[0].click();", chooseFirstCheapestDepartingFlight);

        WebElement returningFlightsPageHeading = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Best returning flights')]")));
        Assert.assertEquals(returningFlightsPageHeading.getText(), "Best returning flights", "");
        System.out.println(returningFlightsPageHeading.getText() + " is displayed as expected");
        verifyObjectDisplayed(returningFlightsPageHeading);

    }

    public void pasteFromClipboard(WebElement element, String input) {

        // Set clipboard contents with given input text
        StringSelection selection = new StringSelection(input);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        try {
            String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
            element.sendKeys(del + Keys.chord(Keys.LEFT_CONTROL, "v"));
        } catch (Exception e) {
            fail("It took longer than " + TIMEOUT + " seconds in the attempt to wait for object '" + element + "'!");
        }
    }

    public void verifyObjectDisplayed(WebElement element) {
        try {
            System.out.println(element + " shows as displayed.");
        } catch (Exception e) {
            fail("It took longer than " + TIMEOUT + " seconds in the attempt to wait for object '" + element.toString() + "'!");
        }
    }

}
