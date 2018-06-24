package me.brokenearth.core.container;

import com.sun.istack.internal.NotNull;

import java.io.File;

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
 */
public final class Container {
    /**
     * The container file
     */
    private static File file;
    /**
     * Specifies whether the container is registered or not
     */
    private static boolean isRegistered = false;
    /**
     * The container class
     */
    private static Container container = null;
    public Container(@NotNull File file) throws NullPointerException, InterruptedException {
        if (isRegistered) {
            throw new InterruptedException("Container is already registered");
        }
        if (!file.exists()) {
            throw new NullPointerException("File does not exist");
        }
        if (!file.isDirectory()) {
            throw new InterruptedException("File is not a directory");
        }
        this.file = file;
        isRegistered = true;
        container = this;
    }
    /**
     * Gets the file and
     * @return it
     */
    public static File getFile() {
        return file;
    }
    /**
     * Gets the container class and
     * @return it
     */
    public static Container getContainer() {
        return container;
    }
}
