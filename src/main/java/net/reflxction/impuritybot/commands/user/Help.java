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
package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.commands.miscs.CommandInfo;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.util.ArrayList;
import java.util.List;

public class Help extends AbstractCommand {

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        User u = event.getMember().getUser();
        JDA j = event.getJDA();
        Guild g = event.getGuild();
        Message m = event.getMessage();
        EmbedBuilder embed = new EmbedFactory(new EmbedBuilder())
                .setRandomColor()
                .addField("Admin commands", convert(getAdminCommands()))
                .addField("Minecraft commands", convert(getMinecraftCommands()))
                .addField("Leveling commands", convert(getLevelCommands()))
                .addField("Fun commands", convert(getFunCommands()))
                .addField("User commands", convert(getUserCommands()))
                .addField("Help commands", convert(getHelpCommands()))
                .addField("Credits commands", convert(getCreditsCommands()))
                .addField("Points commands (WIP)", convert(getPointsCommands()))
                .addField("Others", convert(getOthers()))
                .setFooter("For information on any command, use -command <command>", null)
                .build();
        PrivateChannel pm = u.openPrivateChannel().complete();
        pm.sendMessage(embed.build()).queue();
        m.addReaction("\uD83D\uDCE5").queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{};
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.HELP;
    }

    @Override
    public String getDescription() {
        return "Show the bot commands";
    }

    @Override
    public String getUsage() {
        return "-help";
    }

    @Override
    public long getDelay() {
        return 0;
    }

    private List<AbstractCommand> getAdminCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.ADMIN) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getMinecraftCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.MINECRAFT) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getLevelCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.LEVEL) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getFunCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.FUN) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getUserCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.USER) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getHelpCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.HELP) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getCreditsCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.CREDITS) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getCalendarCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.CALENDAR) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getPointsCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.POINTS) {
                commands.add(command);
            }
        }
        return commands;
    }

    private List<AbstractCommand> getOthers() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (AbstractCommand command : CommandInfo.getCommands()) {
            if (command.getCategory() == CommandCategory.OTHERS) {
                commands.add(command);
            }
        }
        return commands;
    }

    private String convert(List<AbstractCommand> commands) {
        StringBuilder builder = new StringBuilder();
        if (commands.size() != 0) {
            for (int i = 0; i < commands.size(); i++) {
                final AbstractCommand command = commands.get(i);
                builder.append(command.getCommand());
                if (i != commands.size() - 1) {
                    builder.append(", ");
                }
            }
        } else {
            return "None!";
        }
        return builder.toString();
    }

}
