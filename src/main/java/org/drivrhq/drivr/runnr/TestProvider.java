package org.drivrhq.drivr.runnr;

import org.drivrhq.drivr.utils.JsonUtils;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class TestProvider {

    private static final String TEST_DATA_LOCATION = File.separator + "testdata" + File.separator;
    private static final String JSON_EXTENSION = ".json";

    /**
     * DataProvider to return the parent class instance.
     *
     * @param superMethod Test method invoking this method.
     * @return instance of the super methods parameter
     */
    @DataProvider(name = "CoreDataProvider")
    protected static Object[][] getCoreDataProvider(Method superMethod, ITestContext context) {

        JsonUtils jsonUtils = new JsonUtils(TEST_DATA_LOCATION
                + context.getName() + JSON_EXTENSION);

        try {
            Object newUser = jsonUtils.createObjectFromJsonFile(superMethod
                    .getParameterTypes()[0].newInstance());

            return new Object[][]{{ newUser }};

        } catch (Exception e) {
            throw new DrivrInterruptionException("Could not return object for "
                    + superMethod.getParameterTypes()[0] + "...");
        }
    }

    /**
     * DataProvider to return the parent class instance in an array.
     *
     * @param superMethod Test method invoking this method.
     * @return instance of the super methods parameter
     */
    @DataProvider(name = "CoreDataProviderList")
    protected static Iterator<Object[]> getCoreDataProviderArray(Method superMethod,
                                                                 ITestContext context) {

        Class<?> superParameterType = superMethod.getParameterTypes()[0];

        JsonUtils jsonUtils = new JsonUtils(TEST_DATA_LOCATION
                + context.getName() + JSON_EXTENSION);

        List<Object> newUsers;

        newUsers = jsonUtils.createObjectsFromJsonFile(superParameterType);

        List<Object[]> objects = new ArrayList<>();
        for (Object user : newUsers) {
            objects.add(new Object[]{user});
        }
        return objects.iterator();

    }
}
