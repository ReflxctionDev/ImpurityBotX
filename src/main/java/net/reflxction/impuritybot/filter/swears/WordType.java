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
package net.reflxction.impuritybot.filter.swears;

/**
 * The swear words type (regex and normal)
 */
public enum WordType {

    // A regex swear word
    REGEX("R", "Regex"),

    // A normal swear word
    NORMAL("N", "Normal");

    // The starting letter for the type
    private String starter, name;

    /**
     * @param starter The starting character
     * @param name The full type name
     */
    WordType(String starter, String name) {
        this.starter = starter;
        this.name = name;
    }

    /**
     * The starting character
     *
     * @return The starting character of the word type
     */
    public String getStarter() {
        return starter;
    }

    /**
     * The type name
     *
     * @return The full name of the type
     */
    public String getName() {
        return name;
    }

    /**
     * Parses a word's type
     *
     * @param word Word to parse for
     * @return The word type related to the word
     */
    public static WordType parseType(String word) {
        for (WordType type : values()) {
            if (word.startsWith(type.starter)) {
                return type;
            }
        }
        return null;
    }

    /**
     * The string without the word type
     *
     * @param text Text to strip
     * @return The string without the type
     */
    public static String getWithoutType(String text) {
        if (text.isEmpty()) return "";
        if (!text.startsWith("R") || !text.startsWith("N")) return text;
        return text.substring(1);
    }

}
