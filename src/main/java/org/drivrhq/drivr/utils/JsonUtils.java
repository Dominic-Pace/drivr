package org.drivrhq.drivr.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.drivrhq.drivr.utils.exception.DrivrInterruptionException;

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
public class JsonUtils {

    protected String jsonFilePath;
    protected FileReader reader;
    protected Gson gson;

    protected final String resourcesPath = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "test" + File.separator + "resources" + "%s";

    /**
     * Constructor
     *
     * @param jsonFilePath path to json file
     */
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

    /**
     * Method used to create an object list from a json file.
     *
     * @param objectToCreate class type of object to create
     * @return list of objects
     */
    public List<Object> createObjectsFromJsonFile(Class<?> objectToCreate) {
        gson = new GsonBuilder().create();
        return gson.fromJson(reader, new CoreObjectType<>(objectToCreate) );

    }
}
