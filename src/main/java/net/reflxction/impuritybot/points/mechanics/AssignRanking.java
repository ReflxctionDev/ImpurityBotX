/*
 *Copyright 2018 github.com/BrokenEarthDev
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
 */

package net.reflxction.impuritybot.points.mechanics;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.points.NumberRange;
import net.reflxction.impuritybot.points.PointsRank;
import net.reflxction.impuritybot.utils.GuildUtils;
import net.reflxction.impuritybot.utils.data.IgnManager;

/**
 * Assigns the ranking to a player - Just make an instance of this class and use method assignRating()
 * @author BrokenEarth
 */
public class AssignRanking {

    /**
     * The member that you want to assign rating
     */
    private Member member;

    /**
     * An instance of IgnManager
     */
    private IgnManager ignManager = new IgnManager();

    /**
     * The player's level
     */
    private int level;

    /**
     * @param member the desired member
     * @param level the member's level according to PointsRank
     * @see PointsRank
     */
    public AssignRanking(Member member, int level) {
        this.member = member;
        this.level = level;
    }

    /**
     * Sets their rating and their ign as their nickname
     * Format: [rating] IGN
     */
    public void assignRating() {
        String nickname;
        PointsRank pointsRank = PointsRank.getByLevel(level);
        if (pointsRank == PointsRank.A_RATE) nickname = "[A] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.B_RATE) nickname = "[B] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.C_RATE) nickname = "[C] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.D_RATE) nickname = "[D] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.E_RATE) nickname = "[E] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.F_RATE) nickname = "[F] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.S_RATE) nickname = "[S] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.SS_RATE) nickname = "[SS] " + ignManager.getIGN(member.getUser());
        else if (pointsRank == PointsRank.SSS_RATE) nickname = "[SSS] " + ignManager.getIGN(member.getUser());
        else {
            throw new IllegalArgumentException("Cannot set ranting according to level");
        }
        GuildUtils.controller().setNickname(member, nickname).queue();
    }
}

