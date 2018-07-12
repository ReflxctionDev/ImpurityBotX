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
package net.reflxction.impuritybot.core.info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.io.File;

/**
 * Class which consists of roles information
 */
public enum EnumRoles implements IEnumInfo {

    R1("Guild Master // Owner", "Owner(s) of the guild. There are currently four main owners: <@348284085971451904>, <@249739574799106049>, <@272814454784262145> and <@149763661630537729>. You can DM them for help or if you have any questions."),
    R2("Co Owner", "The right hand of guild masters. There are currently no Co-Owners of the guild. However, they have the same permissions as the Guild Masters."),
    R3("Impure Bot", "The guild bot, managed by <@211459080860991488> and <@348165612880789508>. You can read more about the discord bot down below about its features and commands. Has the permission to kick."),
    R4("Developer", "Developers of the guild, has the same permissions as admins. The current developers are <@211459080860991488>, <@348165612880789508> and <@247859560189132800>."),
    R5("Admin", "Current discord admins. Admins have access to all extra commands and channels, including <#374104070945767424> and <#431830317087850497>. Feel free to approach them with any questions and reports."),
    R6("Officer", "In game officers of the guild. Has access to <#374104070945767424> and <#431830317087850497>, and moderation commands like -warn and -kick. Contact them for help or to report a helper/trial officer."),
    R7("Helper", "Discord helpers. They moderate chat and make sure everyone has a good experience. Helpers have access to -warn and -kick."),
    R8("Muted", "Given to those who are temporarily muted. They can view and read messages, but cannot send messages in text channels. However, they can connect to Voice Channels and speak."),
    R9("WotW", "This is given to a winner of a select event. Rewards including credits and the role will be announced along with details of the event. Expires in a week."),
    R10("GvG Captain", "GvG captains. You can contact them or an admin to join the GvG discord and to tryout. You can read more about the GvG team in the Teams section of the FAQ."),
    R11("Team Member", "Any member who is considered an official member of one of the previously mentioned teams"),
    R12("Trusted Member", "Trusted members who aren't staff, but are mature, trusted and well-known, or have contributed a lot to the guild. They also have to be in the discord for a long time.\n"),
    R13("Impurity Member", "A guild member of Impurity"),
    R14("Ally", "Members of guilds allied with Impurity. This includes members of SGA. (Supreme guild Alliance)"),
    R15("Friends", "Non-guild members who have been on the discord for a long time and have proven themselves trustable and mature."),
    R16("Waiting List", "Members of the guild who are on the waiting list."),
    R17("Discord Member", "Any discord member. This role is given for people who have registered"),
    R18("Unregistered", "Non-registered discord members. This role is given when you first join our Discord, and as you have it you will have restricted access to the Discord until you register with the process (going to be mentioned later)"),
    R19("Beep Boop", "Discord Bots. Includes Rythm, Fred Boat, Impurity Support, Impurity Bot,  chroniko, Tatsumaki, Oxyl, and Dank Memer."),
    R20("DJ", "Players who have access to !loop and !disconnect for the Rythm bot.");

    private String role, description;

    EnumRoles(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public EmbedBuilder getAsEmbed() {
        return new EmbedFactory(new EmbedBuilder())
                .addField(getRole(), getDescription())
                .setRandomColor()
                .build();
    }


    public static File getImage() {
        return EnumImages.ROLES.getFile();
    }

}
