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
package net.reflxction.impuritybot.bridge.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.bridge.Bridge;
import net.reflxction.impuritybot.bridge.BridgesManager;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;

public class BridgeInfo extends AbstractCommand {

    private BridgesManager manager = new BridgesManager();

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    @Override
    public String getCommand() {
        return "bridgeinfo";
    }

    /**
     * Process of the command
     *
     * @param j    Instance of the latest JDA cache
     * @param g    Guild that the command is run in
     * @param m    Message of the command
     * @param c    Channel that the message was sent in
     * @param u    User who sent the command
     * @param args Extra arguments of the command
     */
    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        switch (args.length) {
            case 0:
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
                break;
            case 1:
                try {
                    int id = Integer.parseInt(args[0]);
                    Bridge bridge = manager.getBridgeById(id);
                    if (bridge != null) {
                        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                                .setColor(Color.WHITE)
                                .addField("Bridge " + id,
                                        "**ID:** " + bridge.getId() +
                                                "\n**Channels:** <#" + bridge.getChannel1().getId() + ">, <#" + bridge.getChannel2().getId() + ">" +
                                                "\n**Guilds:** " + bridge.getChannel1().getGuild().getName() + ", " + bridge.getChannel2().getGuild().getName())
                                .build();
                        c.sendMessage(builder.build()).queue();
                    } else {
                        c.sendMessage("**Couldn't find a bridge with this ID!**").queue();
                    }
                } catch (NumberFormatException e) {
                    c.sendMessage("**Expected a number, but found `" + args[0] + "`**!").queue();
                }
                break;
        }
    }

    /**
     * Aliases
     *
     * @return A string array that consists of other strings which should do the same process
     */
    @Override
    public String[] getAliases() {
        return new String[0];
    }

    /**
     * Category
     *
     * @return Command category
     */
    @Override
    public CommandCategory getCategory() {
        return CommandCategory.BRIDGES;
    }

    /**
     * Delay
     *
     * @return Delay of the command
     */
    @Override
    public long getDelay() {
        return 0;
    }

    /**
     * Description
     *
     * @return A brief command description
     */
    @Override
    public String getDescription() {
        return "Get information about a specific bridge";
    }

    /**
     * Usage
     *
     * @return Command usage
     */
    @Override
    public String getUsage() {
        return "-bridgeinfo <bridge id>";
    }
}
