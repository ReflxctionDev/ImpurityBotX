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
package net.reflxction.impuritybot.eros.shop;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.eros.ErosItem;

public class Menu extends AbstractCommand {

    @Override
    public String getCommand() {
        return "menu";
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
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
        c.sendMessage(builder.build()).queue();
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
