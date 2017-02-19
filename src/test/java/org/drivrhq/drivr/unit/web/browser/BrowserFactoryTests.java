package org.drivrhq.drivr.unit.web.browser;

import org.drivrhq.drivr.web.browser.BrowserFactory;
import org.drivrhq.drivr.web.browser.enums.BrowserType;
import org.drivrhq.drivr.web.browser.enums.DriverRunType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

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
public class BrowserFactoryTests {

    private BrowserFactory browserFactory;
    private WebDriver driver;
    private String driverType;

    @AfterMethod
    public void cleanUpAfterTest() {
        driver.quit();
    }

    @Test
    public void chromeDriverInstanceTest() {
        browserFactory = new BrowserFactory(BrowserType.CHROME);

        driver = browserFactory.getBrowserInstance(DriverRunType.DIRECT);
        driverType = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.equals("chrome"),
                "The browser type was " + driverType + " but was expected to be chrome.");
    }

    @Test
    public void firefoxDriverInstanceTest() {
        browserFactory = new BrowserFactory(BrowserType.FIREFOX);

        driver = browserFactory.getBrowserInstance(DriverRunType.DIRECT);
        driverType = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.equals("firefox"),
                "The browser type was " + driverType + " but was expected to be firefox.");
    }

    @Test
    public void safariDriverInstanceTest() {
        browserFactory = new BrowserFactory(BrowserType.SAFARI);

        driver = browserFactory.getBrowserInstance(DriverRunType.DIRECT);
        driverType = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.equals("safari"),
                "The browser type was " + driverType + " but was expected to be safari.");
    }
}
