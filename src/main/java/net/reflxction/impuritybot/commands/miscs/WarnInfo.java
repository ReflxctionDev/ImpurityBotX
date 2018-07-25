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
package net.reflxction.impuritybot.commands.miscs;

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
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.StringUtils;
import net.reflxction.impuritybot.data.warnings.WarningManagerImpl;

/**
 * Created by Reflxction, on 01/29/18.
 */
public class WarnInfo extends AbstractCommand {

    private ImpurityBot bot;

    public WarnInfo(ImpurityBot bot) {
        this.bot = bot;
    }

    private final WarningManagerImpl wu = new WarningManagerImpl();



    @Override
    public String getCommand() {
        return "warninfo";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        if (args.length == 0) {
            c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
        }
        if (args.length == 1) {
            try {
                int i = Integer.parseInt(args[0]);
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setDescription("Warning " + i + " for user " + u.getName())
                        .addField("Reason", wu.getWarningReason(u, i))
                        .addField("Warned by", wu.getWarner(u, i).getName())
                        .addField("Total warnings", wu.getWarnings(u) + "")
                        .setRandomColor()
                        .build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a number, but found** `" + args[0] + "`").queue();
            } catch (IllegalArgumentException ex) {
                c.sendMessage("**Couldn't find a warning with this number!**").queue();
            }
        }
        if (args.length == 2) {
            try {
                User warned = j.getUserById(StringUtils.mentionToId(args[0]));
                int i = Integer.parseInt(args[1]);
                EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                        .setDescription("Warning " + i + " for user " + warned.getName())
                        .addField("Reason", wu.getWarningReason(warned, i))
                        .addField("Warned by", wu.getWarner(warned, i).getName())
                        .addField("Total warnings", wu.getWarnings(warned) + "")
                        .setRandomColor()
                        .build();
                c.sendMessage(builder.build()).queue();
            } catch (NumberFormatException ex) {
                c.sendMessage("**Invalid arguments! Expected a user mention, but found** `" + args[0] + "`").queue();
            } catch (IllegalArgumentException ex) {
                c.sendMessage("**Couldn't find a warning with this number!**").queue();
            }
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
    public String getDescription() {
        return "Get information about a specific warning";
    }

    @Override
    public String getUsage() {
        return "-warninfo <@user> <warn number> / -warninfo <warn number>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
