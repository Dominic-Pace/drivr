package org.drivrhq.drivr.unit.web.page;

import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.drivrhq.drivr.web.page.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * (C) Copyright 2017 Dominic Pace (https://github.com/Dominic-Pace)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
public class PageTests extends PageBaseTest {


    @Test
    public void urlParameterValidationTest() {
        Assert.assertTrue(driver.getCurrentUrl().equals(baseURL),
                "The current URL is " + driver.getCurrentUrl()
                        + "but was expected to be: " + baseURL);

    }

    @Test
    public void waitForPageCompleteStateTest() {
        try {
            samplePage.waitForPageCompleteState();
        } catch (Exception e) {
            throw new DrivrInterruptionException("Could not wait for the page complete "
                    + "state for the web page: " + baseURL);

        }
    }

    @Test
    public void waitForCustomPageCompleteStateTest() {
        try {
            samplePage.waitForPageCompleteState(8);

        } catch (Exception e) {
            throw new DrivrInterruptionException("Could not wait for the page complete "
                    + "state for the web page: " + baseURL + " to load in 8 seconds");

        }
    }

    @Test
    public void getUIElementTest() {
        UIElement searchBar = samplePage.getUIElement(By.id("search_query_top"));

        try {
            searchBar.waitForElementToBeVisible().sendKeys("Printed Chiffon Dress");

        } catch (ElementNotVisibleException e) {
            throw new DrivrInterruptionException("Could not find the element: "
                    + "By.id search_query_top");

        }

        Assert.assertTrue(searchBar.getValue().equals("Printed Chiffon Dress"),
                "The search bar text found was " + searchBar.getValue()
                        + "and was expected to contain: Printed Chiffon Dress");

    }

    @Test
    public void refreshPageTest() {
        UIElement searchBar = samplePage.getUIElement(By.id("search_query_top"));

        try {
            searchBar.waitForElementToBeVisible().sendKeys("Printed Chiffon Dress");

        } catch (ElementNotVisibleException e) {
            throw new DrivrInterruptionException("Could not find the element: "
                    + "By.id search_query_top");

        }

        Assert.assertTrue(searchBar.getValue().equals("Printed Chiffon Dress"),
                "The search bar text found was " + searchBar.getValue()
                        + "and was expected to contain: Printed Chiffon Dress");

        samplePage.refreshPage();

        Assert.assertTrue(searchBar.waitForElementToBeVisible().getValue().equals(""),
                "The search bar text found was " + searchBar.getValue()
                        + "and was expected to contain nothing at all!");
    }

    @Test
    public void browserBackButtonTest() {
        samplePage.getUIElement(By.xpath("//a[@title='Women']")).click();

        Assert.assertTrue(driver.getTitle().equals("Women - My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected to be: Women - My Store");

        samplePage.clickBrowserBackButton();

        Assert.assertFalse(driver.getTitle().equals("Women - My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected not to be: Women - My Store");

        Assert.assertTrue(driver.getTitle().equals("My Store"),
                "The page title was " + driver.getTitle()
                        + " but was expected not to be: My Store");

    }
}
