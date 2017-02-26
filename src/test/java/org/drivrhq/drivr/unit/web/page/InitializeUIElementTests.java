package org.drivrhq.drivr.unit.web.page;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

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
public class InitializeUIElementTests extends PageBaseTest {

    @Test
    public void initializeUIElementByIDTest() {
        try {
            samplePage.getUIElement(By.id("search_query_top"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by ID: "
                    + "search_query_top");

        }
    }

    @Test
    public void initializeUIElementByXPathTest() {
        try {
            samplePage.getUIElement(By.xpath("//img[@alt='My Store']"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by XPath: "
                    + "//img[@alt='My Store']");

        }
    }

    @Test
    public void initializeUIElementByCssSelectorTest() {
        try {
            samplePage.getUIElement(By.cssSelector(".btn.btn-default.button-search"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by CSS Selector: "
                    + ".btn.btn-default.button-search");

        }
    }

    @Test
    public void initializeUIElementByLinkTextTest() {
        try {
            samplePage.getUIElement(By.linkText("Ecommerce software by PrestaShop™"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by Link Text: "
                    + "Ecommerce software by PrestaShop™");

        }
    }

    @Test
    public void initializeUIElementByClassNameTest() {
        try {
            samplePage.getUIElement(By.className("homeslider-description"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by Class Name: "
                    + "homeslider-description");

        }
    }

    @Test
    public void initializeUIElementByTagNameTest() {
        try {
            samplePage.getUIElement(By.tagName("tagName"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by Class Name: tagName");

        }
    }

    @Test
    public void initializeUIElementByNameTest() {
        try {
            samplePage.getUIElement(By.name("Test Name"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by Name: Test Name");

        }
    }

    @Test
    public void initializeUIElementByPartialLinkTextTest() {
        try {
            samplePage.getUIElement(By.partialLinkText("software by PrestaShop"));

        } catch (Exception e) {
            throw new RuntimeException("Could not find element by Partial Link Text Name: "
                    + "software by PrestaShop");

        }
    }
}
