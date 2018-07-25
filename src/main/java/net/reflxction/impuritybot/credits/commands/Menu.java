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
package net.reflxction.impuritybot.credits.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.credits.enums.ErosItem;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;

public class Menu extends AbstractCommand {

    @Override
    public String getCommand() {
        return "menu";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        User u = event.getMember().getUser();
        TextChannel c = ((TextChannel) event.getChannel());
        /* EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setDescription("**Shop Menu**\n")
                .addField("Impure Chest",
                        "**Description**: " + ErosItem.IMPURE_CHEST.getDescription() +
                                "\n**ID**: " + ErosItem.IMPURE_CHEST.getId() +
                                "\n**Price**: " + ErosItem.IMPURE_CHEST.getPrice())
                .addBlankField()
                .addField("Impure Can",
                        "**Description**: " + ErosItem.IMPURE_CAN.getDescription() +
                                "\n**ID**: " + ErosItem.IMPURE_CAN.getId() +
                                "\n**Price**: " + ErosItem.IMPURE_CAN.getPrice())
                .addBlankField()
                .addField("Rich Boi (Role)",
                        "**Description**: " + ErosItem.RICH_BOI.getDescription() +
                                "\n**ID**: " + ErosItem.RICH_BOI.getId() +
                                "\n**Price**: " + ErosItem.RICH_BOI.getPrice())
                .addBlankField()
                .addField("Gangster (Role)",
                        "**Description**: " + ErosItem.GANGSTER_ROLE.getDescription() +
                                "\n**ID**: " + ErosItem.GANGSTER_ROLE.getId() +
                                "\n**Price**: " + ErosItem.GANGSTER_ROLE.getPrice())
                .addBlankField()
                .addField("Money Baller (Role)",
                        "**Description**: " + ErosItem.MONEY_BALLER.getDescription() +
                                "\n**ID**: " + ErosItem.MONEY_BALLER.getId() +
                                "\n**Price**: " + ErosItem.MONEY_BALLER.getPrice())

                .setRandomColor()
                .build();
               */
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setTitle("Shop menu")
                .setColor(Color.decode("#e84118"))
                .setDescription("Requested by " + u.getName())
                .addField("Impure Chest", "**Description**: " + ErosItem.IMPURE_CHEST.getDescription() + "\n" +
                        "**ID**: " + ErosItem.IMPURE_CHEST.getId() + "\n" +
                        "**Price**: " + + ErosItem.IMPURE_CHEST.getPrice(), false)
                .addField("Impure can","**Description**: " + ErosItem.IMPURE_CAN.getDescription() + "\n" +
                        "**ID**L " + ErosItem.IMPURE_CAN.getId() + "\n" +
                        "**Price**: " + ErosItem.IMPURE_CAN.getPrice())
                .addField("Gangster (Role) ", "**Description**: "+ ErosItem.RICH_BOI.getDescription() + "\n" +
                        "**ID**: " + ErosItem.RICH_BOI.getId() + "\n" +
                        "**Price**: " + ErosItem.GANGSTER_ROLE.getPrice())
                .addField("Money Baller (Role)", "**Description**: " + ErosItem.MONEY_BALLER.getDescription() + "\n" +
                        "**ID**: " + ErosItem.MONEY_BALLER.getId() + "\n" +
                        "**Price**: " + ErosItem.MONEY_BALLER.getPrice())
                .build();
        try {
            u.openPrivateChannel().complete().sendMessage(builder.build()).queue();
        } catch (Exception e) {
            c.sendMessage(builder.build()).queue();
        }
     }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CREDITS;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Show the shop menu";
    }

    @Override
    public String getUsage() {
        return "-menu";
    }
}
