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
package net.reflxction.impuritybot.commands.dev;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

import java.awt.*;
import java.util.List;

public class RoleInfo extends AbstractCommand {

    /**
     * Name of the command, prefixed with "-"
     *
     * @return ^
     */
    @Override
    public String getCommand() {
        return "roleinfo";
    }

    /**
     * Process of the command
     *
     * @param event the command event instance
     * @param args  the arguments of the command
     */
    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel c = event.getChannel();
        switch (args.length) {
            case 0:
                c.sendMessage("**Incorrect command usage. Try " + getUsage() + "**").queue();
                break;
            default:
                StringBuilder builder = new StringBuilder();
                for (String arg : args) {
                    builder.append(arg).append(" ");
                }
                String roleName = builder.toString().trim();
                List<Role> roles = event.getGuild().getRolesByName(roleName, true);
                if (roles.size() == 0) {
                    c.sendMessage("**Couldn't find a role with this name**").queue();
                } else {
                    roles.forEach(r -> {
                        Color color = r.getColor() == null ? new Color(255, 255, 255) : r.getColor();
                        c.sendMessage("**Name**: " + r.getName() + "\n**Color**: rgb(" + color.getRed() + ", " + color.getBlue() + ", " + color.getBlue() + ")\n**ID**: " + r.getId() + "\n**Creation time**: " + r.getCreationTime().getMonthValue() + "/" + r.getCreationTime().getDayOfMonth() + "/" + r.getCreationTime().getYear()).queue();
                    });
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
        return CommandCategory.DEV;
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
        return "Get the role ID of a specific role";
    }

    /**
     * Usage
     *
     * @return Command usage
     */
    @Override
    public String getUsage() {
        return "-roleid <role name>";
    }
}
