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

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.bridge.Bridge;
import net.reflxction.impuritybot.bridge.BridgesManager;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.utils.lang.StringUtils;

public class BridgeCommand extends AbstractCommand {

    private BridgesManager manager = new BridgesManager();

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    @Override
    public String getCommand() {
        return "bridge";
    }

    /**
     * Process of the command
     *
     * @param j    Instance of the current JDA cache
     * @param g    Guild that the command is run in
     * @param m    Message of the command
     * @param c    Channel that the message was sent in
     * @param u    User who sent the command
     * @param args Extra arguments of the command
     */
    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        if (g.getMember(u).getRoles().get(0).getPositionRaw() >= Roles.ADMIN.getPositionRaw()) {
            switch (args.length) {
                case 0:
                case 1:
                    c.sendMessage("**Incorrect command usage! Try " + getUsage() + "**").queue();
                    break;
                case 2:
                    switch (args[0]) {
                        case "remove":
                            try {
                                int id = Integer.parseInt(args[1]);
                                Bridge bridge = manager.getBridgeById(id);
                                if (bridge != null) {
                                    manager.removeBridge(bridge);
                                } else {
                                    c.sendMessage("**Couldn't find a bridge with this ID!**").queue();
                                }
                            } catch (NumberFormatException e) {
                                c.sendMessage("**Expected a number, but found `" + args[1] + "`**!").queue();
                            }
                            break;
                    }
                    break;
                case 3:
                    switch (args[0]) {
                        case "add":
                            Channel channel1 = j.getTextChannelById(StringUtils.channelToId(args[1]));
                            Channel channel2 = j.getTextChannelById(StringUtils.channelToId(args[2]));
                            Bridge bridge = new Bridge(channel1, channel2, manager.getBridgesSize() + 1);
                            manager.createBridge(bridge);
                            c.sendMessage("**A new bridge has been created with the ID `" + bridge.getId() + "`**. For information about a bridge, use `-bridgeinfo <id>").queue();
                            break;
                    }
                    break;
            }
        } else {
            c.sendMessage("**You don't have permission to manage bridges!**").queue();
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
     * @see CommandCategory
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
        return "Manage bridges between the discord";
    }

    /**
     * Usage
     *
     * @return Command usage
     */
    @Override
    public String getUsage() {
        return "-bridge <add> <channel A> <channel B> | -bridge remove <bridge id>";
    }
}
