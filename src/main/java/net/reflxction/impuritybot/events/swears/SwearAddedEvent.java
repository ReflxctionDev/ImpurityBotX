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
package net.reflxction.impuritybot.events.swears;

/**
 * Fired when a swear is added to the list
 */
public class SwearAddedEvent extends SwearEvent {

    // The word that was added
    private String word;

    /**
     * Initializes a new event
     *
     * @param word Word that was added
     */
    public SwearAddedEvent(String word) {
        this.word = word;
    }

    /**
     * The word
     *
     * @return The word that was added to the list
     */
    public String getWord() {
        return word;
    }
}
