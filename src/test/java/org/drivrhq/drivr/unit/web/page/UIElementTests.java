package org.drivrhq.drivr.unit.web.page;

import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.drivrhq.drivr.utils.exception.DrivrNotSupportedException;
import org.drivrhq.drivr.web.browser.BrowserFactory;
import org.drivrhq.drivr.web.browser.enums.BrowserType;
import org.drivrhq.drivr.web.browser.enums.DriverRunType;
import org.drivrhq.drivr.web.page.Page;
import org.drivrhq.drivr.web.page.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * (C) Copyright 2017 Dominic Pace (https://github.com/Dominic-Pace)
 * ----------------------------------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * ----------------------------------------------------------------------------
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * ----------------------------------------------------------------------------
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class UIElementTests {

    private WebDriver driver;
    private Page samplePage;
    protected String baseURL = "http://automationpractice.com/index.php";

    private UIElement searchBox;
    private UIElement searchBtn;

    @AfterTest
    public void cleanupDriverAfterTest() {
        driver.quit();

    }

    @BeforeTest
    public void setupWebDriverBeforeTest() {
        BrowserFactory factory = new BrowserFactory(BrowserType.CHROME);

        driver = factory.getBrowserInstance(DriverRunType.DIRECT);

    }

    @BeforeMethod
    public void setupWebPageBeforeEachMethod() {
        samplePage = new Page(driver, baseURL);

        searchBox = samplePage.getUIElement(By.id("search_query_top"));
        searchBtn = samplePage.getUIElement(By.xpath("//button[@name='submit_search']"));

    }

    @Test
    public void sendKeysTest() {
        try {
            searchBox.sendKeys("Test Keys");

            throw new DrivrInterruptionException("The Drivr not supported exception is not valid.");
        } catch (DrivrNotSupportedException e) {

        }
    }

    @Test
    public void sendTextAndGetValueTest() {
        searchBox.sendText("Test Keys");

        Assert.assertTrue(searchBox.getValue().equals("Test Keys"),
                "The value in the search box was " + searchBox.getValue()
                        + " but was expected to be: Test Keys");

    }

    @Test
    public void submitTest() {

        Assert.assertTrue(driver.getTitle().equals("My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: My Store");

        searchBox.sendText("Test Keys").submit();

        Assert.assertTrue(driver.getTitle().equals("Search - My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: Search - My Store");

    }

    @Test
    public void clickTest() {

        Assert.assertTrue(driver.getTitle().equals("My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: My Store");

        searchBox.sendText("Test Keys");
        searchBtn.click();

        Assert.assertTrue(driver.getTitle().equals("Search - My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: Search - My Store");

    }

    @Test
    public void clearTest() {
        searchBox.sendText("Test Keys");

        Assert.assertTrue(searchBox.getValue().equals("Test Keys"),
                "The value in the search box was " + searchBox.getValue()
                        + " but was expected to be: Test Keys");

        searchBox.clear();

        Assert.assertTrue(searchBox.getValue().equals(""),
                "The value in the search box was " + searchBox.getValue()
                        + " but was expected to be: -nothing-");
    }
}
