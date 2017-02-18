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
 * (C) Copyright 2016 Dominic Pace (https://github.com/Dominic-Pace)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
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
