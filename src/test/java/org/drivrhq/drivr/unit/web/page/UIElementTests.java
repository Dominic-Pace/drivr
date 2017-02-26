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
    private UIElement sampleCheckbox;
    private UIElement sampleTextField;

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
        sampleCheckbox = samplePage.getUIElement(By.id("layered_category_4"));
        sampleTextField = samplePage.getUIElement(
                By.xpath(".//*[@id='cmsinfo_block']//strong[@class='dark']"));

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

    @Test
    public void clearAndSendTextTest() {
        searchBox.sendText("Test Keys");

        Assert.assertTrue(searchBox.getValue().equals("Test Keys"),
                "The value in the search box was " + searchBox.getValue()
                        + " but was expected to be: Test Keys");

        searchBox.clearAndSendText("New Clear and Send Text");

        Assert.assertTrue(searchBox.getValue().equals("New Clear and Send Text"),
                "The value in the search box was " + searchBox.getValue()
                        + " but was expected to be: New Clear and Send Text");

    }

    @Test
    public void getTagNameTest() {

        Assert.assertTrue(searchBox.getTagName().equals("input"),
                "The value in the search box was " + searchBox.getTagName()
                        + " but was expected to be: input");

        Assert.assertTrue(searchBtn.getTagName().equals("button"),
                "The value in the search box was " + searchBox.getTagName()
                        + " but was expected to be: button");

    }

    @Test
    public void getAttributeTest() {

        Assert.assertTrue(searchBox.getAttribute("id").equals("search_query_top"),
                "The value in the search box was " + searchBox.getAttribute("id")
                        + " but was expected to be: search_query_top");

        Assert.assertTrue(searchBox.getAttribute("class").equals("search_query form-control ac_input"),
                "The value in the search box was " + searchBox.getAttribute("class")
                        + " but was expected to be: search_query form-control ac_input");

        Assert.assertTrue(searchBox.getAttribute("type").equals("text"),
                "The value in the search box was " + searchBox.getAttribute("type")
                        + " but was expected to be: text");

        Assert.assertTrue(searchBox.getAttribute("value").equals(""),
                "The value in the search box was " + searchBox.getAttribute("value")
                        + " but was expected to be: -nothing-");

        Assert.assertTrue(searchBox.getAttribute("placeholder").equals("Search"),
                "The value in the search box was " + searchBox.getAttribute("placeholder")
                        + " but was expected to be: Search");

        Assert.assertTrue(searchBox.getAttribute("name").equals("search_query"),
                "The value in the search box was " + searchBox.getAttribute("name")
                        + " but was expected to be: search_query");

        Assert.assertTrue(searchBox.getAttribute("autocomplete").equals("off"),
                "The value in the search box was " + searchBox.getAttribute("autocomplete")
                        + " but was expected to be: off");

    }

    @Test
    public void isSelectedTest() {
        samplePage.getUIElement(By.xpath("//a[@title='Women']")).click();

        Assert.assertTrue(driver.getTitle().equals("Women - My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: Women - My Store");

        Assert.assertFalse(sampleCheckbox.isSelected(),
                "Sample Checkbox was selected and was expected not to be.");

        sampleCheckbox.click();

        Assert.assertTrue(sampleCheckbox.isSelected(),
                "Sample Checkbox was selected and was expected not to be.");
    }

    @Test
    public void isDisplayedTest() {

        Assert.assertTrue(searchBox.isDisplayed(),
                "The search box was not displayed and was expected to be.");

        Assert.assertTrue(searchBtn.isDisplayed(),
                "The search button was not displayed and was expected to be.");

    }

    @Test
    public void isEnabledTest() {

        Assert.assertTrue(searchBox.isEnabled(),
                "The search box was not enabled and was expected to be.");

        Assert.assertTrue(searchBtn.isEnabled(),
                "The search button was not enabled and was expected to be.");

    }

    @Test
    public void isClickableTest() {

        Assert.assertTrue(searchBtn.isClickable(),
                "The search box was not enabled and was expected to be.");

    }

    @Test
    public void getTextTest() {

        Assert.assertTrue(sampleTextField.getText().equals("This is a custom block of text"),
                "The sample text field contained the text: " + sampleTextField.getText()
                        + " but was expected to contain: This is a custom block of text");

    }
}