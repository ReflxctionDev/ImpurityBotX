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

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.GuildUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which handles and manages caches
 *
 * @author Reflxction
 */
public class CacheHandler {

    /**
     * Starts the cache process
     *
     * @return List of all user caches so it updates
     */
    public List<ProfileAdapterCache> startCache() {
        List<ProfileAdapterCache> list = new ArrayList<>();
        for (Member m : GuildUtils.members()) {
            final User user = m.getUser();
            list.add(new ProfileAdapterCache(user));
        }
        return list;
    }

    /**
     * Updates all available caches
     *
     * @return List of all the up-to-date caches
     */
    public List<ProfileAdapterCache> updateCache() {
        List<ProfileAdapterCache> list = new ArrayList<>();
        for (Member m : GuildUtils.members()) {
            final User user = m.getUser();
            list.add(new ProfileAdapterCache(user));
        }
        return list;
    }

}
