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

package net.reflxction.impuritybot.core.commands;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.commands.miscs.CommandInfo;
import net.reflxction.impuritybot.core.events.Event;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.main.ImpurityBot;

public class RegistryBuilder {

    private JDA j;

    public RegistryBuilder(JDA jda) {
        this.j = jda;
    }

    public RegistryBuilder registerCommand(final AbstractCommand command) {
        j.addEventListener(command);
        final String name = command.getCommand();
        final CommandCategory category = command.getCategory() == null ? CommandCategory.OTHERS : command.getCategory();
        final String description = command.getDescription() == null ? "None set" : command.getDescription();
        final String usage = command.getUsage() == null ? "None set" : command.getUsage();
        CommandInfo.getCommandNames().add(name);
        CommandInfo.getCategories().add(category.toString());
        CommandInfo.getDesc().add(description);
        CommandInfo.getUsages().add(usage);
        CommandInfo.getCommands().add(command);
        return this;
    }

    public RegistryBuilder disableCommand(AbstractCommand command) {
        j.removeEventListener(command);
        final String name = command.getCommand();
        final CommandCategory category = command.getCategory() == null ? CommandCategory.OTHERS : command.getCategory();
        final String description = command.getDescription() == null ? "None set" : command.getDescription();
        final String usage = command.getUsage() == null ? "None set" : command.getUsage();

        CommandInfo.getNames().remove(name);
        CommandInfo.getCategories().remove(category.toString());
        CommandInfo.getDesc().remove(description);
        CommandInfo.getUsages().remove(usage);
        return this;
    }

    public RegistryBuilder registerLogger(Logger logger) {
        j.addEventListener(logger);
        return this;
    }

    public RegistryBuilder disableLogger(Logger logger) {
        j.removeEventListener(logger);
        return this;
    }

    public RegistryBuilder registerListener(ListenerAdapter listener) {
        j.addEventListener(listener);
        return this;
    }

    public RegistryBuilder disableListener(ListenerAdapter listener) {
        j.removeEventListener(listener);
        return this;
    }

    public RegistryBuilder registerListener(EventListener listener) {
        j.addEventListener(listener);
        return this;
    }

    public RegistryBuilder disableListener(EventListener listener) {
        j.removeEventListener(listener);
        return this;
    }

    public RegistryBuilder registerBotEvent(Object event) {
        ImpurityBot.EVENT_BUS.register(event);
        return this;
    }

    public RegistryBuilder unregisterBotEvent(Object event) {
        ImpurityBot.EVENT_BUS.unregister(event);
        return this;
    }

    public RegistryBuilder callEvent(Event event) {
        ImpurityBot.EVENT_BUS.post(event);
        return this;
    }

}
