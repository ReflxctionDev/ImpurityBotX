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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Interface that swear words classes will implement
 */
public interface ISwearWord {

    /**
     * Whether the swear should be checked using regex.
     *
     * @return True if the word needs regex
     */
    boolean isRegex();

    /**
     * The word content
     *
     * @return The word content
     */
    String getWord();

    /**
     * Whether the swear word is contained inside the content
     *
     * @param content Content to check for
     * @return True if the word is contained, false if otherwise.
     */
    default boolean containsWord(String content) {
        if (isRegex()) {
            String pattern = "\\b" + getWord() + (Character.isLetter(content.charAt(content.length() - 1)) ? "\\b" : "");
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);
            return m.find();
        } else {
            return content.contains(getWord());
        }
    }

}
