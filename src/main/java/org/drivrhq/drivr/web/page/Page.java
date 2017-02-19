package org.drivrhq.drivr.web.page;

import com.google.common.base.Preconditions;
import org.drivrhq.drivr.utils.StringUtils;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
public class Page {

    protected WebDriver driver;
    protected String url;
    private WebDriverWait wait;

    protected final int DEFAULT_TIMEOUT = 10;

    public Page(WebDriver driver) {
        this.driver = Preconditions.checkNotNull(driver);
    }

    public Page(WebDriver driver, String url) {
        this.driver = Preconditions.checkNotNull(driver);
        this.url = StringUtils.checkNotNull(url);

        driver.get(url);
    }

    /**
     * Method used to wait for the Page Complete Load State with JavaScript Executor.
     */
    public void waitForPageCompleteState() {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT).until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState")
                            .equals("complete"));
        } catch(NullPointerException e) {
            throw new DrivrInterruptionException("Could not wait for the page complete state.");
        }
    }

    /**
     * Method used to wait for the Page Complete Load State with JavaScript Executor via custom
     * timeout.
     *
     * @param customTimeout integer representation of a custom timeout.
     */
    public void waitForPageCompleteState(int customTimeout) {
        try {
            new WebDriverWait(driver, customTimeout).until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState")
                            .equals("complete"));

        } catch(NullPointerException e) {
            throw new DrivrInterruptionException("Could not wait for the page complete state.");

        }
    }

    public UIElement getUIElement(By byLocator) {
        return Preconditions.checkNotNull(UIElement.getUIElement(byLocator, driver));

    }

    /**
     * Method used to refresh the page.
     */
    public void refreshPage() {
        driver.navigate().refresh();

    }

    /**
     * Method used to click the back button on the browser.
     */
    public void clickBrowserBackButton() {
        driver.navigate().back();

    }

    /**
     * Method used to scroll to the bottom of a web page.
     */
    public void scrollToBottomOfPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");

    }
}
