package org.drivrhq.drivr.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;

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
public class StringUtils {

    private static final int STANDARD_STRING_LENGTH = 6;
    /**
     * Method used to check a String is not null
     *
     * @param stringToCheck some String to check
     * @return String representation of String passed in.
     */
    public static String checkNotNull(String stringToCheck) {

        Preconditions.checkNotNull(stringToCheck);

        return stringToCheck;
    }

    /**
     * Method used to create a random string given a string name.
     *
     * @param randomStringToAppend - String to add onto.
     * @return newly redacted string.
     */
    public static String createRandomAlpha(String randomStringToAppend) {
        return checkNotNull(randomStringToAppend + "_"
                + RandomStringUtils.random(STANDARD_STRING_LENGTH));
    }

    /**
     * Method used to create a random string given a string name of a custom length.
     *
     * @param randomStringToAppend - String to add onto
     * @param stringLength - custom length of string
     * @return newly redacted string.
     */
    public static String createRandomAlpha(String randomStringToAppend, int stringLength) {
        return checkNotNull(randomStringToAppend + "_" + RandomStringUtils.random(stringLength));
    }

    /**
     * Method used to create a random string.
     *
     * @return newly redacted string.
     */
    public static String createRandomAlpha() {
        return checkNotNull(RandomStringUtils.random(STANDARD_STRING_LENGTH));
    }

    /**
     * Method used to create a random string of a custom length.
     *
     * @param stringLength - custom length of string
     * @return newly redacted string.
     */
    public static String createRandomAlpha(int stringLength) {
        return checkNotNull(RandomStringUtils.random(stringLength));
    }

}
