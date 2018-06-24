package me.brokenearth.core.cache;

import java.util.LinkedHashMap;
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
 * @param <K> The key
 * @param <V> The value
 */
public final class Cache<K, V> extends LinkedHashMap<K, V> {
    /**
     * The serial version UID
     */
    private final long serialVersionUID = -4794975964067719956L;
    /**
     * The size of the cache
     */
    private int size;

    Cache(int size) {
        super(size, 0.75F, true);
        this.size = size;
    }
    /**
     * Gets the size of the cache and
     * @return it
     */
    public int getSize() {
        return size;
    }
    /**
     * Clears the cache
     */
    public void clear() {
        super.clear();
    }
    /**
     * Creates a new cache
     * @param size the size of the cache
     * @param <K> The key
     * @param <V> The value
     * @return the new cache's instance
     */
    public static <K, V> Cache<K, V> createNewCache(int size) {
        return new Cache<>(size);
    }
    /**
     * Checks whether it should remove the eldest entry
     * @param entry The map entry
     * @return a boolean, whether the caches size is greater than the HashMap's size
     */
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > size;
    }
}
