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
 * Class which contains teams information
 */
public enum EnumTeams implements IEnumInfo {

    GVG("Skywars/Bedwars GvG Team", "There are two separate teams, the bedwars GvG and skywars GvG teams. To get accepted into a team, you will have to duel one of the respective GvG team leaders by joining the GvG discord. You can ask an admin or a GvG captain for an invite. For any questions regarding the GvG team, contact one of the team captains. If you see a GvG team member hacking, do not hesitate to report him to a staff member along with evidence of said guild member cheating. The GvG teams are led by <@184684243417432064>, <@280641656339038208>, and <@188088666583859200>."),
    APPS("Applications Team", "Led by <@149763661630537729>, the Applications Team is a group of Impurity Staff and Members who review and test prospective players for the Guild. They handle contacting applicants through the forums as to whether or not they are accepted, testing them if need be and inviting them In-Game to the guild. They are knowledgeable of the Guild requirements and understand the criteria to make a special exception for an applicant. You can join by contacting <@149763661630537729>. Accepted members will be released in waves."),
    EVENTS("Events Team", "The events team aims at creating and proposing ideas for eventsbus, and implement said eventsbus, making sure everyone has fun and everything runs smoothly. It is also responsible for taking feedback from members/participants of eventsbus, and to tailor events to the interests and preferences of Impurity members. It is led by <@149763661630537729> and <@348284085971451904>. You can join by contacting them. Like the applications team, accepted members will be released in waves."),
    YOUTUBE("YouTube Team", "The YouTube team is led by <@230315430785318912>. This team is in charge of editing raw videos submitted by guild members, including adding intro, outro, and special effects. In order to get accepted, one has to show the team one of their videos they have made previously. To apply, contact <@230315430785318912>."),
    HELPERS("Helpers Team", "The Helpers team is in charge of keeping the discord channel safe, making sure if everyone is following the rules we have set. Discord helpers, as their name suggests, \"help\" inside of the discord. They moderate chat and have the ability to warn. They are care for their fellow guild mates and are knowledgeable of the rules, and generally how the guild functions. They are mature and can take a joke, and can make fair decisions. The application form will be released regularly and accepted helpers are released in waves."),
    DESIGN("Design Team", "The Design team is in charge of creating guild-exclusive stuff. This consists of GFX designs, partially helps in YouTube thumbnails, aesthetic/attractive renders, and also consists of coding-based projects (e.g bots, mods, etc.). It is currently headed by <@278518950302711808> and <@211459080860991488>.");

    private String name, job;

    EnumTeams(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public EmbedBuilder getAsEmbed() {
        return new EmbedFactory(new EmbedBuilder())
                .addField(getName(), getJob())
                .setRandomColor()
                .build();
    }


    public static File getImage() {
        return EnumImages.TEAMS.getFile();
    }

}
