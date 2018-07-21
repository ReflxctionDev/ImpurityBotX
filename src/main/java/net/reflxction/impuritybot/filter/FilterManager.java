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
package net.reflxction.impuritybot.filter;

import net.reflxction.impuritybot.events.swears.FilterStateChangedEvent;
import net.reflxction.impuritybot.events.swears.SwearAddedEvent;
import net.reflxction.impuritybot.events.swears.SwearRemovedEvent;
import net.reflxction.impuritybot.filter.swears.ISwearWord;
import net.reflxction.impuritybot.filter.swears.NormalSwear;
import net.reflxction.impuritybot.filter.swears.RegexSwear;
import net.reflxction.impuritybot.filter.swears.WordType;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.DataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which manages the filter and its words
 */
@SuppressWarnings("WeakerAccess")
public class FilterManager {

    // Bot instance
    private ImpurityBot bot = ImpurityBot.getBot();

    // Data manager
    private DataManager manager = new DataManager(bot);

    /**
     * Whether the anti-swear is on or not
     *
     * @return True if the anti-swear is on, false if otherwise
     */
    public boolean getState() {
        return bot.getSwearsFile().getBoolean("Enabled");
    }

    /**
     * Sets whether the filter should be enabled or not
     *
     * @param flag Boolean to set
     */
    public void setEnabled(boolean flag) {
        FilterStateChangedEvent event = new FilterStateChangedEvent(flag);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            bot.getSwearsFile().set("Enabled", flag);
            manager.saveFile(bot.getSwearsFile(), "swears");
        }
    }

    /**
     * Updates the swears list in the config
     *
     * @param newList New list value
     */
    private void updateList(List<String> newList) {
        bot.getSwearsFile().set("Swears", newList);
        manager.saveFile(bot.getSwearsFile(), "swears");
    }

    /**
     * Adds a swear word to the swears list
     *
     * @param word Word to add
     * @param type Type of the word
     * @see WordType
     */
    public void addSwearWord(String word, WordType type) {
        SwearAddedEvent event = new SwearAddedEvent(word);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            List<String> words = bot.getSwearsFile().getStringList("Swears");
            words.add(type.getStarter() + word);
            updateList(words);
        }
    }

    /**
     * Finds the correct word type
     *
     * @param word Word to find for
     * @return The word type for this word, {@code null} if not a swear word
     */
    private WordType findTypeFor(String word) {
        List<String> words = bot.getSwearsFile().getStringList("Swears");
        for (String w : words) {
            if (w.substring(1).equalsIgnoreCase(word)) {
                return WordType.parseType(w);
            }
        }
        return null;
    }

    /**
     * Whether a word is a swear word
     *
     * @param word Word to check for
     * @return {@code true} if the given word is a swear, {@code false} if otherwise.
     */
    public boolean isSwearWord(String word) {
        return findTypeFor(word) != null;
    }

    /**
     * Removes a swear word from the swears list
     *
     * @param word Word to remove
     * @see FilterManager#addSwearWord(String, WordType)
     */
    public void removeSwearWord(String word) {
        SwearRemovedEvent event = new SwearRemovedEvent(word);
        ImpurityBot.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            if (isSwearWord(word)) {
                WordType type = findTypeFor(word);
                String included = (type != null ? type.getStarter().toUpperCase() : null) + word;
                List<String> words = bot.getSwearsFile().getStringList("Swears");
                words.remove(included);
                updateList(words);
            }
        }
    }

    public List<ISwearWord> getWords() {
        List<ISwearWord> words = new ArrayList<>();
        List<String> config = bot.getSwearsFile().getStringList("Swears");
        for (String word : config) {
            word = WordType.getWithoutType(word);
            words.add(WordType.parseType(word) == WordType.REGEX ? new RegexSwear(WordType.getWithoutType(word)) : new NormalSwear(WordType.getWithoutType(word)));
        }
        return words;
    }

}
