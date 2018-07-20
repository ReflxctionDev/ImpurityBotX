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
package net.reflxction.impuritybot.commands.admin.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.listeners.discord.MuteManager;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.MuteDuration;
import net.reflxction.impuritybot.utils.MuteDuration.DurationParseException;
import net.reflxction.impuritybot.utils.lang.StringUtils;

/**
 * A remake of {@link Mute}
 */
public class MuteRemake extends AbstractCommand {

    private MuteManager manager = new MuteManager();

    @Override
    public String getCommand() {
        return "mmute";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJda();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        Member sender = g.getMember(u);
        if (sender.getRoles().contains(Roles.MUTE_ACCESS)) {
            if (args.length < 3) {
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
            } else {
                User target;
                try {
                    String id = StringUtils.mentionToId(args[0]);
                    target = j.getUserById(id);
                    if (g.getMember(target).getRoles().get(0).getPositionRaw() < sender.getRoles().get(0).getPositionRaw()) {
                        String parse = args[1];
                        MuteDuration duration = new MuteDuration(parse);
                        manager.muteUser(target, duration.getTime());
                        ImpurityBot.getImpurityGuild().getController().addSingleRoleToMember(ImpurityBot.getImpurityGuild().getMember(target), Roles.MUTED).queue();
                        StringBuilder reason = new StringBuilder();
                        for (int i = 2; i < args.length; i++) {
                            String arg = args[i] + " ";
                            reason.append(arg);
                        }
                        PrivateChannel pm = target.openPrivateChannel().complete();
                        pm.sendMessage("You have been muted by **" + u.getName() + "** for **" + duration.getNiceName() + "**. Reason: **" + reason.toString() + "**").queue();
                    } else {
                        c.sendMessage("**You can't mute people higher than you!**").queue();
                    }
                } catch (DurationParseException e) {
                    c.sendMessage("**Invalid duration, should be <number><unit> (e.g `5m` which is 5 minutes), but found **`" + args[1] + "`").queue();
                } catch (NumberFormatException e) {
                    c.sendMessage("**Expected a user mention (or id), but found** `" + args[0] + "`**!**").queue();
                }

            }
        } else {
            c.sendMessage("**You don't have permission to mute members!**").queue();
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Mute a user";
    }

    @Override
    public String getUsage() {
        return "-mmute <@user> <duration> <reason>";
    }
}
