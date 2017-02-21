
package org.drivrhq.drivr.unit.web.browser;

import org.drivrhq.drivr.web.browser.Capabilities;
import org.drivrhq.drivr.web.browser.enums.BrowserType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

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
public class CapabilitiesTests {

    private DesiredCapabilities capabilities;

    @Test
    public void testChromeCapabilities() {
        capabilities = Capabilities.getBrowserCapabilities(BrowserType.CHROME);

        Assert.assertTrue(capabilities.getBrowserName().equals("chrome"),
                "The browser name expected was " + capabilities.getBrowserName()
                        + " but was expected to be: chrome");

        Assert.assertTrue(capabilities.getCapability("chrome.switches").toString()
                        .contains("--disable-application-cache"),
                "The capability chrome switches was "
                        + capabilities.getCapability("chrome.switches")
                        + " and expected it contains: --disable-application-cache");
        Assert.assertTrue(capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS).toString()
                        .equals("true"),
                "The capability accept SSL Certs was "
                        + capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS)
                        + " and was expected to be: true");

    }

    @Test
    public void testFirefoxCapabilities() {
        capabilities = Capabilities.getBrowserCapabilities(BrowserType.FIREFOX);

        FirefoxProfile profile = (FirefoxProfile) capabilities.getCapability(FirefoxDriver
                .PROFILE);

        Assert.assertTrue(capabilities.getBrowserName().equals("firefox"),
                "The browser name expected was " + capabilities.getBrowserName()
                        + " but was expected to be: firefox");

        Assert.assertTrue(profile.getStringPreference("network.automatic-ntlm-auth.trusted-uris",
                "http://, https//").equals("http://, https//"),
                "The capability for network automatic-ntln-auth trusted uris was "
                        + profile.getStringPreference("network.automatic-ntlm-auth.trusted-uris",
                        "http://, https//") + " and was expected to be: http://, https//");

        Assert.assertFalse(profile.getBooleanPreference("browser.helperApps.alwaysAsk.force",
                false),
                "The capability for browser helperApps alwaysAsk force was "
                        + profile.getBooleanPreference("browser.helperApps.alwaysAsk.force",
                        false)
                        + " and was expected to be: false");

    }

    @Test
    public void testIECapabilities() {
        capabilities = Capabilities.getBrowserCapabilities(BrowserType.IE);

        Assert.assertTrue(capabilities.getBrowserName().equals("internet explorer"),
                "The browser name expected was " + capabilities.getBrowserName()
                        + " but was expected to be: internet explorer");

        Assert.assertTrue(capabilities.getCapability(InternetExplorerDriver
                .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS).toString().equals("true"),
                "The capability for Introduce flakiness by ignoring security domains was "
                    + capabilities.getCapability(InternetExplorerDriver
                        .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS).toString()
                        + " but was expected to be: true");

        Assert.assertTrue(capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS).toString()
                        .equals("true"),
                "The capability for Accept SSL Certificates was "
                        + capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS).toString()
                        + " but was expected to be: true");

    }

    @Test
    public void testSafariCapabilities() {
        capabilities = Capabilities.getBrowserCapabilities(BrowserType.SAFARI);

        Assert.assertTrue(capabilities.getBrowserName().equals("safari"),
                "The browser name expected was " + capabilities.getBrowserName()
                        + " but was expected to be: safari");

        Assert.assertTrue(capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS).toString()
                        .equals("true"),
                "The capability for Accept SSL Certificates was "
                        + capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS).toString()
                        + " but was expected to be: true");

    }
}