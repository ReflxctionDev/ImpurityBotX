package net.reflxction.impuritybot.commands.admin.messages;

import com.sun.javaws.jnl.EmbeddedJNLPValidation;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.others.Roles;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.utils.lang.StringUtils;

import javax.jws.soap.SOAPBinding;
import java.awt.*;

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

public class Say extends AbstractCommand {
    @Override
    public String getCommand() {
        return "say";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        if (!event.getMember().getRoles().contains(Roles.ADMIN)) {
            event.getChannel().sendMessage("**You do not have permission to execute this command**").queue();
            return;
        }
        if (args.length < 1) {
            event.getChannel().sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        String toSay = StringUtils.combine(args);
        event.getMessage().delete().queue();
        event.getChannel().sendMessage(toSay).queue();
        log(event.getMember().getUser(), toSay, event.getChannel());
    }

    private void log(User executor, String content, MessageChannel channel) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setAuthor(executor.getName(), executor.getAvatarUrl(), executor.getAvatarUrl())
                .setTitle(executor.getName() + " used -say")
                .addField("Content", content)
                .setColor(Color.decode("#4cd137"))
                .build();
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Force the bot to say something";
    }

    @Override
    public String getUsage() {
        return "-say <message>";
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
