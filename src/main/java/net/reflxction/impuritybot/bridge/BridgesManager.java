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
package net.reflxction.impuritybot.bridge;

import net.dv8tion.jda.core.entities.Channel;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.DataManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which manages channel bridges
 *
 * @author Reflxction
 */
@SuppressWarnings("WeakerAccess")
public class BridgesManager {

    private ImpurityBot bot = ImpurityBot.getBot();

    private DataManager manager = new DataManager(bot);

    /**
     * Saves a bridge to config
     *
     * @param bridge Bridge to create
     */
    public void createBridge(Bridge bridge) {
        Channel channel1 = bridge.getChannel1();
        Channel channel2 = bridge.getChannel2();
        if (!channel1.getId().equals(channel2.getId())) {
            final String ids = channel1.getId() + " " + channel2.getId();
            bot.getBridgesFile().set("Bridges." + bridge.getId() + ".B", ids);
            manager.saveFile(bot.getBridgesFile(), "bridges");
        }
    }

    /**
     * Removes a bridge from the config
     *
     * @param bridge Bridge to remove
     */
    public void removeBridge(Bridge bridge) {
        Channel channel1 = bridge.getChannel1();
        Channel channel2 = bridge.getChannel2();
        if (!channel1.getId().equals(channel2.getId())) {
            bot.getBridgesFile().set("Bridges." + bridge.getId(), null);
        }
    }

    /**
     * @return An int that returns the current brides amount
     */
    public int getBridgesSize() {
        ConfigurationSection section = bot
                .getBridgesFile()
                .getConfigurationSection("Bridges");
        try {
            return section.getKeys(false) == null ? 0 : section.getKeys(false).size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * A list of all available bridges
     *
     * @return ^
     */
    public List<Bridge> getBridges() {
        List<Bridge> bridges = new ArrayList<>();
        for (int i = 1; i <= getBridgesSize(); i++) {
            String[] ids = bot.getBridgesFile().getString("Bridges." + i + ".B").split(" ");
            Channel channel1 = ImpurityBot.getJDA().getTextChannelById(ids[0]);
            Channel channel2 = ImpurityBot.getJDA().getTextChannelById(ids[1]);
            bridges.add(new Bridge(channel1, channel2, i));
        }
        return bridges;
    }

    /**
     * Get a bridge by an id
     *
     * @param id ID to look for
     * @return Bridge with the given id, null if not found
     */
    public Bridge getBridgeById(int id) {
        for (Bridge bridge : getBridges()) {
            if (bridge.getId() == id) {
                return bridge;
            }
        }
        return null;
    }

    /**
     * If a channel is in a bridge
     *
     * @param channel Channel to look for
     * @return True if the channel is in a bridge, false if not
     */
    public boolean isBridge(Channel channel) {
        for (Bridge bridge : getBridges()) {
            if (bridge.getChannel1().equals(channel) || bridge.getChannel2().equals(channel)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the channel that is in the same bridge as the given channel
     *
     * @param channel Channel to get for
     * @return The channel, null if non are found
     */
    public Channel getCorrespondingChannel(Channel channel) {
        if (isBridge(channel)) {
            for (Bridge bridge : getBridges()) {
                if (bridge.getChannel1().equals(channel)) {
                    return bridge.getChannel2();
                } else if (bridge.getChannel2().equals(channel)) {
                    return bridge.getChannel1();
                }
            }
        } else {
            return null;
        }
        return null;
    }

}
