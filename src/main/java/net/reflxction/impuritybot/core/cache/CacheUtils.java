/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.impuritybot.core.cache;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.util.List;

/**
 * Useful caching utilities
 */
public class CacheUtils {

    private static CacheHandler handler = new CacheHandler();

    /**
     * List of the current cache, not necessarily the latest one
     *
     * @return ^
     */
    public static List<ProfileAdapterCache> getCurrentCache() {
        return ImpurityBot.getCurrentCache().isEmpty() ? handler.updateCache() : ImpurityBot.getCurrentCache();
    }

    /**
     * Returns the current cache for the specified user
     *
     * @param u User to get cache for
     * @return Adapter which contains cache info
     */
    public static ProfileAdapterCache getCacheFor(User u) {
        for (ProfileAdapterCache cache : getCurrentCache()) {
            if (cache.getUser().get().getId().equals(u.getId())) {
                return cache;
            }
        }
        return new ProfileAdapterCache(u);
    }
}
