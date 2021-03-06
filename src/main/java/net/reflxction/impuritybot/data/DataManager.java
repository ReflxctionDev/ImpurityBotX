/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
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
package net.reflxction.impuritybot.data;

import net.reflxction.impuritybot.core.info.EnumImages;
import net.reflxction.impuritybot.main.ImpurityBot;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private ImpurityBot m;

    public DataManager(ImpurityBot bot) {
        m = bot == null ? ImpurityBot.getBot() : bot;
    }

    private void createFile(String fileName) {
        File file = new File(m.getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            m.saveResource(fileName + ".yml", false);
        }
    }

    private void addFile(String name) {
        File file = new File(m.getDataFolder(), name);
        if (!file.exists()) {
            m.saveResource(name, false);
        }
    }

    public void saveFile(FileConfiguration fileConfig, String fileName) {
        try {
            File file = new File(m.getDataFolder(), fileName + ".yml");
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored) {
        }
    }

    private void loadFile(String fileName) {
        File file = new File(m.getDataFolder(), fileName + ".yml");
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveIgnsFile() {
        saveFile(m.getIgnsFile(), "igns");
    }

    public void saveCreditsFile() {
        saveFile(m.getCreditsFile(), "credits");
    }

    public void savePollsFile() {
        saveFile(m.getPollsFile(),
                "polls");
    }

    public void loadFiles() {
        createFile("igns");
        createFile("credits");
        createFile("exp");
        createFile("warnings");
        createFile("polls");
        createFile("points");
        createFile("config");
        createFile("bridges");
        createFile("swears");

        loadFile("igns");
        loadFile("credits");
        loadFile("exp");
        loadFile("warnings");
        loadFile("polls");
        loadFile("points");
        loadFile("config");
        loadFile("bridges");
        loadFile("swears");

        for (EnumImages image : EnumImages.values()) {
            addFile(image.getName());
        }
    }
}
