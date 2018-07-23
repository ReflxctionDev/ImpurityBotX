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
package net.reflxction.impuritybot.commands.admin.roles;

import me.kbrewster.exceptions.APIException;
import me.kbrewster.exceptions.InvalidPlayerException;
import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.hypixelapi.player.HypixelPlayer;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.io.IOException;

import static net.reflxction.impuritybot.utils.lang.APIUtils.API_KEY_STRING;

public class UpdateRoles extends AbstractCommand {
    @Override
    public String getCommand() {
        return "updateroles";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length != 2) {
            EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                    .setDescription("Invalid arguments!\n")
                    .setRandomColor()
                    .setDescription("Try -updateroles <@user> <user's in-game name>")
                    .build();
            channel.sendMessage(embed.build()).queue();
        } else {
            try {
                HypixelAPI api = new HypixelAPI(API_KEY_STRING);
                HypixelPlayer p = api.getPlayer(args[0]);
                final double lvl = p.getAbsoluteLevel();
                //TODO Leveling
                if (lvl < 20) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Bronze", true).get(0)).queue();
                }
                if (lvl > 20 && p.getAbsoluteLevel() < 30) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Silver", true).get(0)).queue();
                }
                if (lvl > 30 && p.getAbsoluteLevel() < 40) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Ruby", true).get(0)).queue();
                }
                if (lvl > 40 && p.getAbsoluteLevel() < 50) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Ruby", true).get(0)).queue();
                }
                if (lvl > 50 && p.getAbsoluteLevel() < 60) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Platinum", true).get(0)).queue();
                }
                if (lvl > 60 && p.getAbsoluteLevel() < 70) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Titanium", true).get(0)).queue();
                }
                if (lvl > 70 && p.getAbsoluteLevel() < 80) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Diamond", true).get(0)).queue();
                }
                if (lvl > 80 && p.getAbsoluteLevel() < 90) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Challenger", true).get(0)).queue();
                }
                if (lvl > 90) {
                    g.getController().addRolesToMember(g.getMember(u), g.getRolesByName("Major", true).get(0)).queue();
                }
            } catch (InvalidPlayerException e) {
                EmbedBuilder embed = new EmbedFactory(new EmbedBuilder()).setRandomColor()
                        .setDescription("Invalid player!\n")
                        .setRandomColor()
                        .setDescription("This player cannot be found. Ensure that the spelling is correct.")
                        .build();
                channel.sendMessage(embed.build()).queue();
            } catch (APIException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MINECRAFT;
    }

    @Override
    public String getDescription() {
        return "Update your roles";
    }

    @Override
    public String getUsage() {
        return "-updateroles <@user> <user's ign>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
