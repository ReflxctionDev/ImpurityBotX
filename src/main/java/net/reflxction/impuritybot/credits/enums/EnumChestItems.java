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
package net.reflxction.impuritybot.credits.enums;

/**
 * List of all the available chest items
 */
public enum EnumChestItems {

    EXP("Exp", 40),

    BOMB("Bomb", 20),

    CAN("Impurity Can", 20);

    private String name;

    private int chance;

    EnumChestItems(String name, int chance) {
        this.name = name;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public int getChance() {
        return chance;
    }
}
