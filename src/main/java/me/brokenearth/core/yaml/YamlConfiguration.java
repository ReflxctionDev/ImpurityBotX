package me.brokenearth.core.yaml;

import com.sun.istack.internal.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

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
 * NOTE: BOTH {@link YamlReader} AND {@link YamlWriter} does not support tree-view or lists
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see YamlReader
 * @see YamlWriter
 */
public final class YamlConfiguration {
    /**
     * The yaml reader class for this config
     */
    private YamlReader reader;
    /**
     * The yaml writer class for this config
     */
    private YamlWriter writer;
    /**
     * The yaml file
     */
    private File file;
    private YamlConfiguration(@NotNull File file) {
        if (!file.getName().endsWith(".yml") && !file.getName().endsWith(".yaml")) {
            try {
                throw new Exception("This is not a yaml file");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        writer = new YamlWriter(this);
        reader = new YamlReader(this);
    }
    /**
     * Gets the yaml file and
     * @return it
     */
    public File getFile() {
        return file;
    }
    /**
     * Use this for an instance of YamlConfiguration
     * @param file the yaml file
     * @return an instance of the YamlConfiguration class
     */
    public static YamlConfiguration loadConfig(@NotNull File file) {
        return new YamlConfiguration(file);
    }
    /**
     * Gets the yaml writer and
     * @return the instance of it
     */
    public YamlWriter getWriter() {
        return writer;
    }
    /**
     * Gets the yaml reader and
     * @return the instance of it
     */
    public YamlReader getReader() {
        return reader;
    }
}
