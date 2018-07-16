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
package net.reflxction.impuritybot.main;

import net.reflxction.impuritybot.bridge.commands.BridgeCommand;
import net.reflxction.impuritybot.bridge.commands.BridgeInfo;
import net.reflxction.impuritybot.commands.admin.botupdates.ReleasedCommand;
import net.reflxction.impuritybot.commands.admin.botupdates.ToDo;
import net.reflxction.impuritybot.commands.admin.messages.*;
import net.reflxction.impuritybot.commands.admin.news.*;
import net.reflxction.impuritybot.commands.admin.roles.UpdateRoles;
import net.reflxction.impuritybot.commands.admin.user.*;
import net.reflxction.impuritybot.commands.fun.exclusive.EightBall;
import net.reflxction.impuritybot.commands.level.LevelCmd;
import net.reflxction.impuritybot.commands.level.RankCmd;
import net.reflxction.impuritybot.commands.level.SetLevel;
import net.reflxction.impuritybot.commands.level.TopLevels;
import net.reflxction.impuritybot.commands.minecraft.IGN;
import net.reflxction.impuritybot.commands.minecraft.IGNOf;
import net.reflxction.impuritybot.commands.minecraft.PlayerIGN;
import net.reflxction.impuritybot.commands.miscs.*;
import net.reflxction.impuritybot.commands.points.ManagePointsCmd;
import net.reflxction.impuritybot.commands.points.PointsBalance;
import net.reflxction.impuritybot.commands.user.*;
import net.reflxction.impuritybot.core.commands.RegistryBuilder;
import net.reflxction.impuritybot.core.eros.Slots;
import net.reflxction.impuritybot.core.listeners.bot.LevelUpListener;
import net.reflxction.impuritybot.eros.*;
import net.reflxction.impuritybot.eros.shop.Menu;
import net.reflxction.impuritybot.logs.channel.ChannelCreatedLogger;
import net.reflxction.impuritybot.logs.channel.ChannelDeletedLogger;
import net.reflxction.impuritybot.logs.message.MessageEditedLogger;
import net.reflxction.impuritybot.logs.roles.RoleCreatedLogger;
import net.reflxction.impuritybot.logs.roles.RoleDeletedLogger;
import net.reflxction.impuritybot.logs.roles.RoleRemovedLogger;
import net.reflxction.impuritybot.logs.server.ServerChangeNameLogger;
import net.reflxction.impuritybot.logs.user.UserBanLogger;
import net.reflxction.impuritybot.logs.user.UserJoinLogger;
import net.reflxction.impuritybot.logs.user.UserLeaveLogger;
import net.reflxction.impuritybot.logs.user.UserNickLogger;
import net.reflxction.impuritybot.logs.user.warnings.UserGivenWarnLogger;
import net.reflxction.impuritybot.logs.user.warnings.UserRemoveWarningLogger;
import net.reflxction.impuritybot.logs.user.warnings.UserWarningsClearedLogger;

import static net.reflxction.impuritybot.main.ImpurityBot.getBot;

/**
 * Registry class
 *
 * @author BrokenEarth & Reflxction
 */
class Register {

    private RegistryBuilder builder = new RegistryBuilder(ImpurityBot.getJDA());

    void registerCommands() {
        builder
                .registerCommand(new Announce())
                .registerCommand(new About())
                .registerCommand(new Delete())
                .registerCommand(new MakeEmbed())
                .registerCommand(new GuildInfo())
                .registerCommand(new HateMe())
                .registerCommand(new Help())
                .registerCommand(new HistoryDelete())
                .registerCommand(new Introduce())
                .registerCommand(new Kick())
                .registerCommand(new NewYear())
                .registerCommand(new Say())
                .registerCommand(new IGN())
                .registerCommand(new IGNOf())
                .registerCommand(new RankCmd())
                .registerCommand(new LevelCmd())
                .registerCommand(new Pin())
                .registerCommand(new CommandInfo())
                .registerCommand(new EightBall())
                .registerCommand(new TopLevels())
                .registerCommand(new Daily())
                .registerCommand(new PunishmentRules())
                .registerCommand(new Unpin())
                .registerCommand(new Warn(getBot()))
                .registerCommand(new UserWarnings(getBot()))
                .registerCommand(new RemoveWarn(getBot()))
                .registerCommand(new ClearWarns(getBot()))
                .registerCommand(new WarnInfo(getBot()))
                .disableCommand(new UpdateRoles())
                .registerCommand(new Invite())
                .registerCommand(new Ping())
                .registerCommand(new UserInfo())
                .registerCommand(new DiscordInfo())
                .registerCommand(new Notify())
                .registerCommand(new Poll(getBot()))
                .registerCommand(new ToDo())
                .registerCommand(new ReleasedCommand())
                .registerCommand(new Balance())
                .registerCommand(new WelcomeManager())
                .registerCommand(new PlayerIGN())
                .registerCommand(new Menu())
                .registerCommand(new Buy())
                .registerCommand(new Credits())
                .registerCommand(new Transfer())
                .registerCommand(new Open())
                .registerCommand(new Agree())
                .registerCommand(new Accept())
                .registerCommand(new Slots())
                .registerCommand(new SetLevel())
                .registerCommand(new Calendar())
                .registerCommand(new MakeCalendar())
                .registerCommand(new PointsBalance())
                .registerCommand(new ManagePointsCmd())
                .registerCommand(new Unmute())
                .registerCommand(new Mute())
                .registerCommand(new MuteRemake())
                //.registerCommand(new StealCommand())
                .registerCommand(new Tester())
                .registerCommand(new BridgeCommand())
                .registerCommand(new BridgeInfo())
                .registerCommand(new BetCommand())
                .registerCommand(new FilterCommand());
        //.registerCommand(new BetAmount());
    }

    void registerLoggers() {
        builder
                .registerLogger(new UserJoinLogger())
                .registerLogger(new UserLeaveLogger())
                .registerLogger(new ChannelCreatedLogger())
                .registerLogger(new ChannelDeletedLogger())
                .registerLogger(new MessageEditedLogger())
                .registerLogger(new UserBanLogger())
                .registerLogger(new UserNickLogger())
                .registerLogger(new ServerChangeNameLogger())
                .registerLogger(new RoleRemovedLogger())
                .registerLogger(new RoleCreatedLogger())
                .registerLogger(new RoleDeletedLogger())
                .registerBotEvent(new UserRemoveWarningLogger())
                .registerBotEvent(new UserGivenWarnLogger())
                .registerBotEvent(new UserWarningsClearedLogger())
                .registerBotEvent(new LevelUpListener());
    }
}


