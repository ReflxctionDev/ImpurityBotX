/*
 * * Copyright 2018 github.com/BrokenEarthDev
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
package net.reflxction.impuritybot.applicants;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;

public class ApplyCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "apply";
    }

    /**
     * Process of the command
     *
     * @param event the command event instance
     * @param args  the arguments of the command
     */
    @Override
    public void process(CommandEvent event, String[] args) {
        User user = event.getMember().getUser();
        MessageChannel channel = event.getChannel();
        if (!channel.equals(user.openPrivateChannel().complete())) {
            channel.sendMessage("**Please head on to my pm and run the apply command there**").queue();
            return;
        }
        if (args.length != 1) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        Apply apply = new Apply(args[0], user);
        apply.apply();
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
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Applies and sends the application to the events team";
    }

    @Override
    public String getUsage() {
        return "-apply <ign>";
    }
}