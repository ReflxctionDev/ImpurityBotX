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

package net.reflxction.impuritybot.core.others;

import net.dv8tion.jda.core.entities.User;

public class UserProfile {

    private long exp;
    private int level;
    private int credits;
    private int warnings;

    private String ign;

    private User user;

    public UserProfile(long exp, int level, int credits, int warnings, String ign, User user) {
        this.exp = exp;
        this.level = level;
        this.credits = credits;
        this.warnings = warnings;
        this.ign = ign;
        this.user = user;
    }

    public long getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getCredits() {
        return credits;
    }

    public int getWarnings() {
        return warnings;
    }

    public String getIgn() {
        return ign;
    }

    public User getUser() {
        return user;
    }
}
