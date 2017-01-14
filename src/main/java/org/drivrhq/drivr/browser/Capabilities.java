package org.drivrhq.drivr.browser;

import org.drivrhq.drivr.browser.enums.BrowserType;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;

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
                throw new DrivrInterruptionException("Cannot resolve the browser capabilties for "
                        + "browser type: " + browserType);

        }
        return capabilities;

    }
}
