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
package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.guild.GuildUtils;

/**
 * Gives the user the tester role
 */
public class Tester extends AbstractCommand {

    @Override
    public String getCommand() {
        return "tester";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        User u = event.getMember().getUser();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        TextChannel c = ((TextChannel) event.getChannel());
        switch (g.getId()) {
            // Main discord
            case "363721897743089668":
                c.sendMessage("**This command can only be used in the development Discord!**").queue();
                break;
            // Development discord
            case "471703262492360725":
                switch (args.length) {
                    case 0:
                        m.addReaction("\uD83D\uDC4C").queue();
                        g.getController().addSingleRoleToMember(g.getMember(u), Roles.TESTER).queue();
                        break;
                    case 1:
                        if (args[0].equalsIgnoreCase("remove")) {
                            g.getController().removeSingleRoleFromMember(g.getMember(u), Roles.TESTER).queue();
                            m.addReaction("\uD83D\uDC4C").queue();
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Receive the Tester role";
    }

    @Override
    public String getUsage() {
        return "-tester / -tester remove";
    }
}
