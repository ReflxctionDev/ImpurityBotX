package me.brokenearth.core.commands;

import com.sun.istack.internal.NotNull;
import me.brokenearth.core.events.CommandEvent;

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
 * @see Command
 * @see CommandEvent
 */
public interface CommandExecutor {
    /**
     * Triggered when someone types the prefix first
     * @param event the event that occurs when a command is executed
     * @param args the argument of the command - split by a regex
     */
    void onCommand(@NotNull CommandEvent event, String[] args);
}
