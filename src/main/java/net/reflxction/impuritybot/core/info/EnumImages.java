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
package net.reflxction.impuritybot.core.info;

import net.reflxction.impuritybot.main.ImpurityBot;

import java.io.File;

/**
 * Class which contains images used.
 */
public enum EnumImages {

    RULES("rules"),
    PUNISHMENTS("punishments"),
    FAQ("faq"),
    ROLES("discord-roles"),
    TEAMS("teams"),
    REGISTRATION("registration"),
    REGISTRATION_EXAMPLE("reg-example"),
    STAFF_NOTES("staff-notes"),;

    private String name;

    EnumImages(String name) {
        this.name = name + ".png";
    }

    private ImpurityBot bot = ImpurityBot.getBot();

    public File getFile() {
        return new File(bot.getDataFolder(), name);
    }

    public String getName() {
        return name;
    }
}
