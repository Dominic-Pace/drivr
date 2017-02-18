package org.drivrhq.drivr.web.page;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.NotImplementedException;
import org.drivrhq.drivr.web.page.interfaces.UIElementInt;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void submit() {

    }


    /**
     * Method used to send text to a web element.
     *
     * @param var1 - variable to send to element
     */
    @Override
    public void sendKeys(CharSequence... var1) {
        driver.findElement(byLocator).sendKeys(var1);

    }

    /**
     * Method used to clear a web element of all text or information.
     *
     * @return UIElement instance.
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

    @Override
    public List<WebElement> findElements(By by) {
        throw new DrivrInterruptionException("Find Element is not supported by UIElement.");

    }

    @Override
    public WebElement findElement(By by) {
        throw new DrivrInterruptionException("Find Element is not supported by UIElement.");

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
        return driver.findElement(byLocator).getRect();

    }

    /**
     * Method used to get the CSS Value of an element
     *
     * @return css value of the element
     */
    @Override
    public String getCssValue(String s) {
        return driver.findElement(byLocator).getCssValue(s);

    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        throw new NotImplementedException("Method called getScreenshotAs() is not yet supported.");

    }

    @Override
    public Coordinates getCoordinates() {
        throw new NotImplementedException("Method called getCoordinates() is not yet supported.");

    }

    /**
     * Method used to get a wrapped selenium element.
     *
     * @return WebElement instance
     */
    @Override
    public WebElement getWrappedElement() {
        return getWrappedElement();

    }

    /**
     * Method used to toggle a checkbox.
     */
    @Override
    public void toggle() {
        getWrappedElement().click();

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
        return getWrappedElement().isSelected();

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
