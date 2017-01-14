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
