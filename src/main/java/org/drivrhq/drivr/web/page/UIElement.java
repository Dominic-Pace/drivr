package org.drivrhq.drivr.web.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.MethodNotSupportedException;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.drivrhq.drivr.utils.exception.DrivrNotSupportedException;
import org.drivrhq.drivr.web.page.interfaces.UIElementInt;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
public class UIElement implements UIElementInt {

    private WebDriver driver;
    protected FluentWait<WebDriver> wait;
    protected By byLocator;
    protected final int sleepTime = 100;
    protected final int DEFAULT_TIMEOUT = 10;

    /**
     * Constructor for UIElement
     *
     * @param byLocator - the by locator used to define the element
     * @param driver - WebDriver instance
     */
    public UIElement(By byLocator, WebDriver driver) {
        this.byLocator = Preconditions.checkNotNull(byLocator);
        this.driver = Preconditions.checkNotNull(driver);
        if (wait == null) {
            wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, sleepTime)
                    .ignoring(WebDriverException.class);

        }
    }

    /**
     * Method used to get a web element via the core wrapper.
     *
     * @param byLocator - the by locator used to define the element
     * @param driver - WebDriver instance
     * @return UIElement instance
     */
    protected static UIElement getUIElement(By byLocator, WebDriver driver) {
        return new UIElement(byLocator, driver);


    }

    /**
     * Method used to click on a web element.
     */
    @Override
    public void click() {
        driver.findElement(byLocator).click();


    }

    /**
     * Method used to submit a web element.
     */
    @Override
    public void submit() {
        driver.findElement(byLocator).submit();

    }

    /**
     * Method used to send text to a web element.
     *
     * @param var1 - variable to send to element
     */
    @Override
    public void sendKeys(CharSequence... var1) {
        throw new DrivrNotSupportedException(
                "Please use sendText method. sendKeys is not supported.");

    }

    /**
     * Method used to send text to a web element.
     *
     * @param textToSend - text to send to the element
     * @return instance of UIElement
     */
    public UIElement sendText(String textToSend) {
        driver.findElement(byLocator).sendKeys(textToSend);
        return this;

    }

    /**
     * Method used to clear a web element of all text or information.
     */
    @Override
    public void clear() {
        driver.findElement(byLocator).clear();

    }

    /**
     * Method used to send text to a web element.
     */
    public void clearAndSendText(String textToSend) {
        driver.findElement(byLocator).clear();
        driver.findElement(byLocator).sendKeys(textToSend);

    }

    /**
     * Method used to get the tag name of a web element.
     */
    @Override
    public String getTagName() {
        return driver.findElement(byLocator).getTagName();


    }

    /**
     * Method used to get the attribute name of a web element.
     *
     * @param attributeString - String to get attribute of.
     */
    @Override
    public String getAttribute(String attributeString) {
        return driver.findElement(byLocator).getAttribute(attributeString);

    }

    /**
     * Method used to determine if an element is selectable or not.
     *
     * @return if the element is selectable or not
     */
    @Override
    public boolean isSelected() {
        return driver.findElement(byLocator).isSelected();

    }

    /**
     * Method used to determine if an element is displayed or not.
     *
     * @return if the element is displayed or not
     */
    @Override
    public boolean isDisplayed() {
        return driver.findElement(byLocator).isDisplayed();

    }

    /**
     * Method used to determine if an element is enabled or not.
     *
     * @return if the element is enabled or not
     */
    @Override
    public boolean isEnabled() {
        return driver.findElement(byLocator).isEnabled();

    }

    /**
     * Method used to determine if an element is clickable or not.
     *
     * @return if the element is clickable or not
     */
    public boolean isClickable() {
        return driver.findElement(byLocator).isDisplayed()
                && driver.findElement(byLocator).isEnabled();

    }

    /**
     * Method used to get the text of an element.
     *
     * @return representation of the text of an element
     */
    @Override
    public String getText() {
        return driver.findElement(byLocator).getText();

    }

    /**
     * Method used to get the value of an element.
     * For Example:
     *      Get the value of text entered into a textbox
     *
     * @return String representation of the value of an element
     */
    public String getValue() {
        return driver.findElement(byLocator).getAttribute("value");

    }

    @Override
    public List<WebElement> findElements(By by) {
        throw new DrivrNotSupportedException("Find Elements is not supported by UIElement.");

    }

    @Override
    public WebElement findElement(By by) {
        throw new DrivrNotSupportedException("Find Element is not supported by UIElement.");

    }

    /**
     * Method used to get the location of an element
     *
     * @return location of the element
     */
    @Override
    public Point getLocation() {
        return driver.findElement(byLocator).getLocation();

    }

    /**
     * Method used to get the size of an element
     *
     * @return size of the element
     */
    @Override
    public Dimension getSize() {
        return driver.findElement(byLocator).getSize();

    }

    /**
     * Method used to get the rectangle of an element
     *
     * @return rectangle of the element
     */
    @Override
    public Rectangle getRect() {
        throw new DrivrNotSupportedException("Rect is not supported by UIElement.");

    }

    /**
     * Method used to get the CSS Value of an element
     *
     * @return css value of the element
     */
    @Override
    public String getCssValue(String s) {
        throw new DrivrNotSupportedException("Get CSS Value is not supported by UIElement.");

    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        throw new DrivrNotSupportedException("Method called getScreenshotAs() is not yet supported.");

    }

    @Override
    public Coordinates getCoordinates() {
        throw new DrivrNotSupportedException("Method called getCoordinates() is not yet supported.");

    }

    /**
     * Method used to get a wrapped selenium element.
     *
     * @return WebElement instance
     */
    @Override
    public UIElement getWrappedElement() {
        throw new DrivrNotSupportedException("Get Wrapped Element is not supported by UIElement.");

    }

    /**
     * Method used to toggle a checkbox.
     */
    @Override
    public void toggle() {
        driver.findElement(byLocator).click();

    }

    /**
     * Method used to check a check box.
     */
    @Override
    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    /**
     * Method used to check a check box based on user input.
     */
    public void check(boolean toCheck) {
        if (toCheck) {
            check();

        } else {
            uncheck();

        }
    }

    /**
     * Method used to uncheck a check box.
     */
    @Override
    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    /**
     * Method used to determine if a checkbox is checked.
     *
     * @return true/false.
     */
    @Override
    public boolean isChecked() {
        return driver.findElement(byLocator).isSelected();

    }

    /**
     * Method used for waiting for an element to be visible.
     *
     * @return UIElement instance
     */
    public UIElement waitForElementToBeVisible() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        return this;

    }

    /**
     * Method used for waiting for an element to be visible with a custom timeout.
     *
     * @param timeout custom timeout time.
     * @return UIElement instance
     */
    public UIElement waitForElementToBeVisible(int timeout) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        return this;

    }

    /**
     * Method used for waiting for an element to be present with the default timeout.
     *
     * @return Core Web UIElement instance
     */
    public UIElement waitForElementToBePresent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return this;

    }

    /**
     * Method used for waiting for an element to be present with a custom timeout.
     *
     * @param timeout custom timeout time.
     * @return Core Web UIElement instance
     */
    public UIElement waitForElementToBePresent(int timeout) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return this;

    }

    /**
     * Method used for waiting for an element to be clickable.
     *
     * @return UIElement instance
     */
    public UIElement waitForElementToBeClickable() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.elementToBeClickable(byLocator));
        return this;

    }

    /**
     * Method used for waiting for an element to be clickable with a custom timeout.
     *
     * @param timeout custom timeout time.
     * @return UIElement instance
     */
    public UIElement waitForElementToBeClickable(int timeout) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(sleepTime, TimeUnit.MILLISECONDS)
                .until(ExpectedConditions.elementToBeClickable(byLocator));
        return this;

    }

    /**
     * Method used to scroll to an element
     *
     * @return UIElement instance
     */
    public UIElement scrollToElement() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(byLocator));
        return this;

    }

    /**
     * Method used to get hidden text by element ID.
     *
     * @return UIElement instance
     */
    public String getHiddenTextById(String elementID) {
        waitForElementToBePresent();

        return (String) ((JavascriptExecutor) driver)
                .executeScript("return document.getElementById('" + elementID + "').innerHTML",
                        driver.findElement(byLocator));

    }
}
