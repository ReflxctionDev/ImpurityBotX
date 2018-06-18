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
package net.reflxction.impuritybot.points;

/**
 * Enum for ranks that you achieve from points
 *
 * @author Reflxction
 */
public enum PointsRank {

    SSS_RATE("SSS Rate", new NumberRange(2500, Integer.MAX_VALUE), 8),

    SS_RATE("SS Rate", new NumberRange(2000, 2499), 7),

    S_RATE("S Rate", new NumberRange(1500, 1999), 6),

    A_RATE("A Rate", new NumberRange(1200, 1499), 5),

    B_RATE("B Rate", new NumberRange(900, 1199), 4),

    C_RATE("C Rate", new NumberRange(600, 899), 3),

    D_RATE("D Rate", new NumberRange(300, 599), 2),

    E_RATE("E Rate", new NumberRange(100, 299), 1),

    F_RATE("F Rate", new NumberRange(0, 99), 0);

    private String rankName;

    private int level;

    private NumberRange range;

    PointsRank(String rankName, NumberRange range, int level) {
        this.rankName = rankName;
        this.range = range;
        this.level = level;
    }

    public String getChar() {
        return getRankName().split(" ")[0];
    }

    public String getRankName() {
        return rankName;
    }

    public NumberRange getRange() {
        return range;
    }

    public int getLevel() {
        return level;
    }

    public static PointsRank getByLevel(int level) {
        for (PointsRank rank : values()) {
            if (rank.getLevel() == level) {
                return rank;
            }
        }
        return level <= 8 ? F_RATE : SSS_RATE;
    }



}
