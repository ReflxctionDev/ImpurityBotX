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
 * Normal swear words that mustn't use regex
 */
public class NormalSwear implements ISwearWord {

    // The swear word
    private String word;

    /**
     * Creates a new swear word
     *
     * @param word Word to create
     */
    public NormalSwear(String word) {
        this.word = word;
    }

    /**
     * Whether the swear should be checked using regex.
     *
     * @return True if the word needs regex
     */
    @Override
    public boolean isRegex() {
        return false;
    }

    /**
     * The swear word
     *
     * @return The swear word
     */
    @Override
    public String getWord() {
        return WordType.getWithoutType(word);
    }

    /**
     * Whether the swear word is in the given content
     *
     * @param source Content to check for
     * @return True if the word is in it
     */
    public boolean isContainedIn(String source) {
        return containsWord(source);
    }
}
