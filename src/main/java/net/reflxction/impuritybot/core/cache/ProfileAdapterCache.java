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
package net.reflxction.impuritybot.core.cache;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.utils.data.*;
import net.reflxction.impuritybot.utils.data.exp.ExpManager;
import net.reflxction.impuritybot.utils.data.level.LevelManager;
import org.apache.commons.lang.Validate;

import java.util.Optional;

/**
 * Adapter which contains all information about a user, making it usable for cache handling
 *
 * @see CacheHandler
 */
public class ProfileAdapterCache {

    private User user;

    private IDataManager[] managers = new IDataManager[]{
            new ExpManager(),
            new LevelManager(),
            new IgnManager(),
            new PointsManager(),
            new WarningsManager(null),
            new CreditsManager()
    };

    private int level, exp, credits, points, warnings;
    private String ign;

    /**
     * Constructor which contains all caches for the user
     *
     * Every time a new instance is made, cache automatically updates
     * @param user
     */
    public ProfileAdapterCache(User user) {
        Validate.notNull(user, "User may not be null");
        this.user = user;
        this.level = getLevelManager().getUserLevel(user);
        this.exp = getExpManager().getUserExp(user);
        this.credits = getCreditsManager().getUserCredits(user);
        this.points = getPointsManager().getUserPoints(user);
        this.warnings = getWarningsManager().getWarnings(user);
        this.ign = getIgnManager().getIGN(user);
    }

    public Optional<User> getUser() {
        return Optional.of(user);
    }

    private ExpManager getExpManager() {
        return ((ExpManager) managers[0]);
    }

    private LevelManager getLevelManager() {
        return ((LevelManager) managers[1]);
    }

    private IgnManager getIgnManager() {
        return ((IgnManager) managers[2]);
    }

    private PointsManager getPointsManager() {
        return ((PointsManager) managers[3]);
    }

    public WarningsManager getWarningsManager() {
        return ((WarningsManager) managers[4]);
    }

    private CreditsManager getCreditsManager() {
        return ((CreditsManager) managers[5]);
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getCredits() {
        return credits;
    }

    public int getPoints() {
        return points;
    }

    public int getWarnings() {
        return warnings;
    }

    public String getIgn() {
        return ign;
    }

    public ProfileAdapterCache updateCache(User user) {
        return new ProfileAdapterCache(user);
    }

}
