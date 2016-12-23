package org.drivrhq.drivr.browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.drivrhq.drivr.browser.enums.BrowserType;
import org.drivrhq.drivr.browser.enums.DriverRunType;
import org.drivrhq.drivr.utils.exception.RunnerInterruptionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
public class BrowserFactory {

    private WebDriver driver;
    private BrowserType browserType;
    private String seleniumGridURL;

    public BrowserFactory(BrowserType browserType) {
        this.browserType = browserType;

    }

    public BrowserFactory(BrowserType browserType, String seleniumGridURL) {
        this.browserType = browserType;
        this.seleniumGridURL = seleniumGridURL;

    }

    /**
     * Method used to initialize a browser instance.
     * @return WebDriver instnace.
     */
    public WebDriver getBrowserInstance(DriverRunType driverRunType) {

        if (driverRunType.equals(DriverRunType.DIRECT)) {
            driver = initDirectDriver(Capabilities.getBrowserCapabilites(browserType));

        } else if (driverRunType.equals(DriverRunType.REMOTE) && seleniumGridURL == null) {
            throw new RunnerInterruptionException("Could not create a remote browser instance "
                    + "because Grid URL is undefined. Please pass as parameter into constructor.");

        } else if (driverRunType.equals(DriverRunType.REMOTE)) {
            driver = initRemoteWebDriver(Capabilities.getBrowserCapabilites(browserType));

        } else {
            throw new RunnerInterruptionException("Could not initialize a browser.");

        }
        return driver;

    }

    /**
     * Method used to initialize a direct web driver instance.
     *
     * @param capabilities of the direct driver.
     * @return WebDriver instance.
     */
    private WebDriver initDirectDriver(DesiredCapabilities capabilities) {

        switch(browserType) {

            case FIREFOX:
                driver = new FirefoxDriver(capabilities);
                break;

            case CHROME:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver(capabilities);
                break;

            case IE:
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver(capabilities);
                break;

            case SAFARI:
                driver = new SafariDriver(capabilities);
                break;

            default:
                throw new RunnerInterruptionException("Cannot resolve driver type: "
                        + browserType.toString());
        }

        return driver;
    }

    /**
     * Method used to initialize a remote webdriver instance.
     *
     * @param capabilities of the remote driver.
     * @return WebDriver instance.
     */
    private WebDriver initRemoteWebDriver(DesiredCapabilities capabilities) {

        try {
            URL url = new URL(seleniumGridURL);
            driver = new RemoteWebDriver(url, capabilities);

        } catch(MalformedURLException e) {
            throw new RunnerInterruptionException(
                    "Cannot initialize a Remote WebDriver instance.");

        }
        return driver;

    }
}
