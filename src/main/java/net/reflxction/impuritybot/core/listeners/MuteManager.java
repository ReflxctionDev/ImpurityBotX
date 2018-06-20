/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *`
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.GuildUtils;
import net.reflxction.impuritybot.utils.data.DataManager;

/**
 * Class which manages user mutes
 *
 * @author Reflxction
 */
public class MuteManager {

    private ImpurityBot bot = ImpurityBot.getBot();
    private DataManager data = new DataManager(bot);

    /**
     * Mutes a user and saves it into the file
     *
     * @param user    User to mute
     * @param seconds Seconds to mute the user for
     * @see net.reflxction.impuritybot.utils.lang.TimeUtils
     */
    public void muteUser(User user, int seconds) {
        bot.getCreditsFile().set("Mutes." + user.getId() + ".Name", user.getName());
        bot.getCreditsFile().set("Mutes." + user.getId() + ".Mute", seconds);
        data.saveFile(bot.getCreditsFile(), "credits");
        Guild g = GuildUtils.guild();
        g.getController().addSingleRoleToMember(g.getMember(user), Roles.MUTED).queue();
    }

    /**
     * Returns the time left for the user, 0 if the user isn't muted.
     *
     * @param u User to get time for
     * @return Time left until the mute is over
     */
    public int getTimeLeftForMute(User u) {
        if (!isMuted(u)) return 0;
        return bot.getCreditsFile().getInt("Mutes." + u + ".Mute");
    }

    /**
     * Returns true if the given user is muted
     *
     * @param u User to check for
     * @return True if the user is muted
     */
    public boolean isMuted(User u) {
        return bot.getCreditsFile().getInt("Mutes." + u + ".Mute") != 0;
    }

    /**
     * Updates the time mute for everyone (which is called every 4 seconds)
     */
    public void updateMuteTime() {
        for (Member m : GuildUtils.members()) {
            final User u = m.getUser();
            if (isMuted(u)) {
                muteUser(u, getTimeLeftForMute(u) - 4);
            } else {
                Guild g = GuildUtils.guild();
                g.getController().removeSingleRoleFromMember(g.getMember(u), Roles.MUTED).queue();
            }
        }
    }

    /**
     * Unmutes a user
     *
     * @param user User to unmute
     */
    public void unmute(User user) {
        if (isMuted(user)) {
            GuildUtils.controller().removeSingleRoleFromMember(GuildUtils.guild().getMember(user), Roles.MUTED).queue();
            bot.getCreditsFile().set("Mutes." + user.getId(), null);
            data.saveCreditsFile();
        }
    }

}
