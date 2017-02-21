package org.drivrhq.drivr.web.browser;

import java.util.Collections;

import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.drivrhq.drivr.web.browser.enums.BrowserType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

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
public class Capabilities {

    /**
     * Method used to get the Browser Capability based on device type.
     *
     * @return the browser capability
     */
    public static DesiredCapabilities getBrowserCapabilities(BrowserType browserType) {

        DesiredCapabilities capabilities;

        switch (browserType) {

            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                FirefoxProfile profile = new FirefoxProfile();

                profile.setAcceptUntrustedCertificates(true);
                profile.setAssumeUntrustedCertificateIssuer(false);
                profile.setPreference(
                        "network.automatic-ntlm-auth.trusted-uris", "http://, https//");
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);

                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                capabilities.setJavascriptEnabled(true);

                break;

            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--start-maximized");

                capabilities.setCapability("chrome.switches",
                        Collections.singletonList("--disable-application-cache"));
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                break;

            case IE:
                capabilities = DesiredCapabilities.internetExplorer();

                capabilities.setCapability(InternetExplorerDriver
                        .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

                break;

            case SAFARI:
                capabilities = DesiredCapabilities.safari();

                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

                break;

            default:
                throw new DrivrInterruptionException("Cannot resolve the browser capabilities for "
                        + "browser type: " + browserType);

        }
        return capabilities;

    }
}
