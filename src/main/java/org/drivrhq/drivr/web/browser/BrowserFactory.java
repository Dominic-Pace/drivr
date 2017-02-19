package org.drivrhq.drivr.web.browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.drivrhq.drivr.web.browser.enums.BrowserType;
import org.drivrhq.drivr.web.browser.enums.DriverRunType;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
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
     * @return WebDriver instance.
     */
    public WebDriver getBrowserInstance(DriverRunType driverRunType) {

        if (driverRunType.equals(DriverRunType.DIRECT)) {
            driver = initDirectDriver(Capabilities.getBrowserCapabilities(browserType));

        } else if (driverRunType.equals(DriverRunType.REMOTE) && seleniumGridURL == null) {
            throw new DrivrInterruptionException("Could not create a remote browser instance "
                    + "because Grid URL is undefined. Please pass as parameter into constructor.");

        } else if (driverRunType.equals(DriverRunType.REMOTE)) {
            driver = initRemoteWebDriver(Capabilities.getBrowserCapabilities(browserType));

        } else {
            throw new DrivrInterruptionException("Could not initialize a browser.");

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
                throw new DrivrInterruptionException("Cannot resolve driver type: "
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
            throw new DrivrInterruptionException(
                    "Cannot initialize a Remote WebDriver instance.");

        }
        return driver;

    }
}
