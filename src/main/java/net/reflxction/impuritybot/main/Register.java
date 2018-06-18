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

import net.reflxction.impuritybot.commands.admin.botupdates.ReleasedCommand;
import net.reflxction.impuritybot.commands.admin.botupdates.ToDo;
import net.reflxction.impuritybot.commands.admin.messages.*;
import net.reflxction.impuritybot.commands.admin.news.*;
import net.reflxction.impuritybot.commands.admin.roles.UpdateRoles;
import net.reflxction.impuritybot.commands.admin.user.*;
import net.reflxction.impuritybot.commands.fun.exclusive.EightBall;
import net.reflxction.impuritybot.commands.hypixel.IGN;
import net.reflxction.impuritybot.commands.hypixel.IGNOf;
import net.reflxction.impuritybot.commands.hypixel.PlayerIGN;
import net.reflxction.impuritybot.commands.hypixel.PlayerRank;
import net.reflxction.impuritybot.commands.level.LevelCmd;
import net.reflxction.impuritybot.commands.level.RankCmd;
import net.reflxction.impuritybot.commands.level.SetLevel;
import net.reflxction.impuritybot.commands.level.TopLevels;
import net.reflxction.impuritybot.commands.miscs.*;
import net.reflxction.impuritybot.commands.user.*;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.eros.Slots;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.eros.*;
import net.reflxction.impuritybot.eros.shop.Menu;
import net.reflxction.impuritybot.logs.channel.ChannelCreatedLogger;
import net.reflxction.impuritybot.logs.channel.ChannelDeletedLogger;
import net.reflxction.impuritybot.logs.message.MessageEditedLogger;
import net.reflxction.impuritybot.logs.roles.RoleCreatedLogger;
import net.reflxction.impuritybot.logs.roles.RoleDeletedLogger;
import net.reflxction.impuritybot.logs.roles.RoleGivenLogger;
import net.reflxction.impuritybot.logs.roles.RoleRemovedLogger;
import net.reflxction.impuritybot.logs.server.ServerChangeNameLogger;
import net.reflxction.impuritybot.logs.user.UserBanLogger;
import net.reflxction.impuritybot.logs.user.UserJoinLogger;
import net.reflxction.impuritybot.logs.user.UserLeaveLogger;
import net.reflxction.impuritybot.logs.user.UserNickLogger;

import static net.reflxction.impuritybot.main.ImpurityBot.bot;

/**
 * Registry class
 *
 * @author BrokenEarthDev
 */
public class Register {

    private static AbstractCommand[] abstractCommands = {new Announce(), new About(), new Delete(), new EmbedMaker(), new GuildInfo(),
            new HateMe(), new Help(), new HistoryDelete(), new Introduce(), new Kick(), new NewYear(), new Say(), new IGN(), new IGNOf(),
            new RankCmd(), new LevelCmd(), new Pin(), new PlayerRank(), new CommandInfo(), new EightBall(), new TopLevels(), new Daily(),
            new PunishmentRules(), new Unpin(), new Warn(bot), new UserWarnings(bot), new RemoveWarn(bot), new ClearWarns(bot), new WarnInfo(bot),
            new UpdateRoles(), new net.reflxction.impuritybot.commands.user.Invite(), new Ping(), new UserInfo(), new DiscordInfo(), new Notify(), new NotifyUpdate(), new Poll(bot),
            new ToDo(), new ReleasedCommand(), new Balance(), new WelcomeManager(bot), new PlayerIGN(), new Menu(), new Buy(), new Credits(),
            new Transfer(), new Open(), new Agree(), new Accept(), new Slots(), new SetLevel(), new Calendar(), new MakeCalendar()};

    private static Logger[] loggers = {new UserJoinLogger(), new UserLeaveLogger(), new ChannelCreatedLogger(), new ChannelDeletedLogger(),
            new MessageEditedLogger(), new UserBanLogger(), new UserNickLogger(), new ServerChangeNameLogger(), new RoleGivenLogger(), new RoleRemovedLogger(),
            new RoleRemovedLogger(), new RoleCreatedLogger(), new RoleDeletedLogger()};

    public static AbstractCommand[] getAbstractCommands() {
        return abstractCommands;
    }

    public static Logger[] getLoggers() {
        return loggers;

    }
}
