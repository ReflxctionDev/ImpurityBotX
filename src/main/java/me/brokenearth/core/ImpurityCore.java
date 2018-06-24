package me.brokenearth.core;

import com.sun.istack.internal.NotNull;
import me.brokenearth.core.commands.Command;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

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
 * Please extend your main class with this class
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 */
public class ImpurityCore extends ListenerAdapter {
    /**
     * The jda
     */
    private static JDA jda;
    /**
     * Registers the jda
     * @param jda the jda that is going to be registered
     */
    protected static void registerJDA(@NotNull JDA jda) {
        ImpurityCore.jda = jda;
        jda.addEventListener(new Command());
    }
    /**
     * Gets the jda and
     * @return it
     */
    public static JDA getJDA() {
        return jda;
    }
}
