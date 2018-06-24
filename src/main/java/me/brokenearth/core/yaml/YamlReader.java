package me.brokenearth.core.yaml;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Copyright 2018 github.com/BrokenEarthDev
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see YamlConfiguration
 * @see YamlWriter
 */
public final class YamlReader {
    /**
     * The map that stores key entries
     */
    private Map<String, Object> map;
    /**
     * The yaml file
     */
    private File file;
    /**
     * A file reader
     */
    private FileReader fileReader;
    YamlReader(YamlConfiguration config) {
        this.file = config.getFile();
        try {
            this.fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.map = config.getWriter().map;
        if (!config.getWriter().saved) getContents().clear();
        try {
            addContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Gets the object from a specified path
     * @param path the path of the object
     * @return the object found
     */
    public Object get(String path) {
        return getContents().get(path);
    }
    /**
     * Gets the integer from a specified path
     * @param path the path of the integer
     * @return the integer found
     */
    public int getInt(String path) {
        return (Integer) getContents().get(path);
    }
    /**
     * Gets the boolean from a specified path
     * @param path the path of the boolean
     * @return the boolean found
     */
    public boolean getBoolean(String path) {
        return (Boolean) getContents().get(path);
    }
    /**
     * Gets the long from a specified path
     * @param path the path of the long
     * @return the long found
     */
    public long getLong(String path) {
        return (Long) getContents().get(path);
    }
    /**
     * Gets the short from a specified path
     * @param path the path of the short
     * @return the short found
     */
    public short getShort(String path) {
        return (Short) getContents().get(path);
    }
    /**
     * Gets the byte from a specified path
     * @param path the path of the byte
     * @return the byte found
     */
    public byte getByte(String path) {
        return (Byte) getContents().get(path);
    }
    /**
     * Gets the contents in the yaml file
     * @return the contents
     */
    public Map<String, Object> getContents() {
        return map;
    }

    /**
     * Adds contents that are are not written using {@link YamlReader}
     * @throws IOException if file not specified correctly
     */
    private void addContents() throws IOException{
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String read = "";
        while ((read = reader.readLine()) != null) {
            if (!getContents().containsKey(getPath(read)) || !getContents().containsValue(getValue(read))) {
                if (!getValue(read).equalsIgnoreCase("") || !getPath(read).equalsIgnoreCase("")) {
                    getContents().put(getPath(read), getValue(read));
                }
            }
        }
    }
    /**
     * Gets the path from a line
     * @param line a complete yaml file
     * @return the path
     */
    public String getPath(String line) {
        char[] chars = line.toCharArray();
        int breakpoint = 0;
        String path = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ':') {
                breakpoint = i;
            }
        }
        for (int i = 0; i < breakpoint; i++) {
            path += chars[i];
        }
        return path;
    }
    /**
     * Gets the value from a line
     * @param line a complete yaml line
     * @return the value
     */
    private String getValue(String line) {
        char[] chars = line.toCharArray();
        int breakpoint = 0;
        boolean nospace = false;
        String value = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ':') breakpoint = i;
        }
        for (int i = breakpoint + 1; i < chars.length; i++) {
            if (chars[i] != ' ') {
                nospace = true;
            }
            if (!nospace) continue;
            value+=chars[i];
        }
        return value;
    }
}
