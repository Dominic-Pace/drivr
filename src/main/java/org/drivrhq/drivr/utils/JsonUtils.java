package org.drivrhq.drivr.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class JsonUtils {

    protected String jsonFilePath;
    protected FileReader reader;
    protected Gson gson;

    protected final String resourcesPath = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "test" + File.separator + "resources" + "%s";

    public JsonUtils(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.jsonFilePath = String.format(resourcesPath, jsonFilePath);

        try {
            this.reader = new FileReader(this.jsonFilePath);

        } catch (FileNotFoundException e) {
            throw new DrivrInterruptionException("Could not read the json file: " + jsonFilePath);

        }
    }

    /**
     * Method used to create a java object from a json file.
     *
     * @param objectToCreate - specified dynamic object (POJO format)
     * @return Plain old Java Object from the JSON file
     */
    public Object createObjectFromJsonFile(Object objectToCreate) {
        gson = new GsonBuilder().create();
        return gson.fromJson(reader, objectToCreate.getClass());

    }

    public List<Object> createObjectsFromJsonFile(Class<?> objectToCreate) {
        gson = new GsonBuilder().create();
        return gson.fromJson(reader, new CoreObjectType<>(objectToCreate) );

    }
}
