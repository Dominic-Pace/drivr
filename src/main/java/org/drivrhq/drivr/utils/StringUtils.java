package org.drivrhq.drivr.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;

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
 * Class stored with commonly used String functions.
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
