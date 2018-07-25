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

public enum CanItems {

    NORMAL_SODA("Normal Soda", 50),

    EXPLODING_SODA("Exploding Soda", 20),

    IMPOVERISHING_SODA("Impoverishing Soda", 10),

    SILENCE_SODA("Silence Soda", 10),

    GENIE_SODA("Genie Soda", 5),

    PANDORA_SODA("Pandora's Soda", 5);

    private String name;
    private int chance;

    CanItems(String name, int chance) {
        this.name = name;
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }

    public String getName() {
        return name;
    }

}
