package net.reflxction.impuritybot.utils.guild;

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

import me.kbrewster.exceptions.APIException;
import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.mojangapi.MojangAPI;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.managers.GuildController;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.IgnManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.reflxction.impuritybot.main.ImpurityBot.getImpurityGuild;

public class GuildUtils {

    public static Guild guild() {
        return getImpurityGuild();
    }

    public static List<Member> members() {
        return guild().getMembers();
    }

    public static JDA JDA() {
        return ImpurityBot.getJDA();
    }

    public static GuildController controller() {
        return guild().getController();
    }

    public static Role roleByName(String name) {
        return guild().getRolesByName(name, true).get(0);
    }

    public static Role roleById(String id) {
        return guild().getRoleById(id);
    }

    private static IgnManager igns = new IgnManager();

    public static final String API_KEY = "fd148cd1-5c96-43e4-8f2e-53ee385d979b";

    private static List<String> players() {
        List<String> players = new ArrayList<>();
        try {
            HypixelAPI api = new HypixelAPI(API_KEY);
            String guildID = null; // Gets Guilds Identifier
            try {
                guildID = api.getGuildID(UUID.fromString("dfbd02a4-6be1-4153-a203-aed7204d35b6"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            me.kbrewster.hypixelapi.guild.Guild guild = null;
            try {
                guild = api.getGuild(guildID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert guild != null;
            guild.getMembers().forEach(m -> {
                try {
                    players.add(MojangAPI.getName(UUID.fromString(MojangAPI.addDashes(m.getUuid()))));
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            });
            return players;
        } catch (Exception ignored) {
        }
        return null;
    }

    private static final List<String> players = players();

    public static boolean isGuildMember(User u) {
        return players != null && players.contains(igns.getIGN(u));
    }
}
