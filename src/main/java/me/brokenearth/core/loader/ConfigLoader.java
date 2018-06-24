package me.brokenearth.core.loader;

import me.brokenearth.core.yaml.YamlConfiguration;

import java.io.File;
import java.lang.reflect.Method;

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
 * @deprecated
 * Class not currently supported
 */
public final class ConfigLoader {

    /**
     * Gets the config file
     * @return the config file
     */
    public static File getConfigFile() {
        if (new File("src/config.yml").exists()) return new File("src/config.yml");
        else if (new File("src/main/resources/config.yml").exists()) {
            return new File("src/main/resources/config.yml");
        }
        else {
            try {
                throw new LoadException("Cannot find or load config.yml");
            } catch (LoadException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Gets the config file's yamlconfiguration and
     * @return it
     */
    public static YamlConfiguration getConfigFileConfiguration() {
        return YamlConfiguration.loadConfig(getConfigFile());
    }

    /**
     * Gets the version declared in the config file
     * @return
     */
    public static String getVersion() {
        return (String) getConfigFileConfiguration().getReader().get("version");
    }

    /**
     * Gets the main class as object and
     * @return it
     */
    public static Object getMainClass() {
        return getConfigFileConfiguration().getReader().get("class");
    }

    /**
     * Gets the main class name and
     * @return it
     */
    public static String getMainClassName() {
        return (String) getConfigFileConfiguration().getReader().get("class");
    }

    /**
     * Runs the onEnable method using the configuration file
     * @throws Exception
     */
    public static void runOnEnable() throws Exception {
        Class clazz = Class.forName(getMainClassName());
        Method method = clazz.getMethod("onEnable");
            method.invoke(clazz.newInstance());
    }
}
