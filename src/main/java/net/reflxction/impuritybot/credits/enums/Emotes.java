package net.reflxction.impuritybot.credits.enums;

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

import net.reflxction.impuritybot.main.ImpurityBot;

public enum Emotes {

    TREASURE("chest"),
    CAN("can"),
    AKINATOR("akinator"),
    DICE("dice"),
    CREDITS("credits"),
    EXP("exp");


    private String name;

    Emotes(String name) {
        this.name = ImpurityBot.getJDA().getGuildById("285409136995336192").getEmotesByName(name, true).get(0).getAsMention();
    }

    public String e() {
        return name;
    }
}
