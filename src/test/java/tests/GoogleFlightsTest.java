package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GoogleFlightsPage;

import java.util.concurrent.TimeUnit;

public class GoogleFlightsTest {

    public WebDriver driver;

    @BeforeMethod

    public void beforeMethod() {

        // Create an instance of the Chrome driver

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Launch the Google Flights website

        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/flights");

    }

    // Test navigates to Google flights homepage, Enters Destination (as Google picks up Origin by location)
    // Enter dates of travel and searched for flights.
    // Selects first best available departing flight and verifies that user is landed on the "Select returning flight" page

    @Test()
    public void verifyGoogleFlightsResponse() {
        GoogleFlightsPage googleFlightsPage = new GoogleFlightsPage();
        String title = driver.getTitle();

        //Print page's title
        System.out.println("Page Title: " + title);

        //Assert that user is on Google Flights homepage
        Assert.assertEquals(title, "Flights", "Title assertion is failed!");

        googleFlightsPage.verifyGoogleFlightsSearchIsWorking(driver);

    }

    @AfterMethod
    public void afterMethod() {
        //Close browser and end the session
        driver.quit();
    }

}
