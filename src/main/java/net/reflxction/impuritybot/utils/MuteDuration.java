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
package net.reflxction.impuritybot.utils;

import net.reflxction.impuritybot.utils.lang.TimeUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which handles mute duration
 *
 * @author Reflxction
 */
@SuppressWarnings("ConstantConditions")
public class MuteDuration {

    // Time to mute for
    private int time;

    // Time unit (e.g seconds, minutes, etc.)
    private TimeUnit unit;

    /**
     * A private constructor for initializing after using regex to parse
     *
     * @param time Time to mute for
     * @param unit Time unit
     */
    private MuteDuration(int time, TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    public MuteDuration(String parse) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(parse);
        while (m.find()) {
            int preTime = Integer.parseInt(m.group());
            this.unit = TimeUnit.getByName(parse.replace(m.group(), "")).get();
            this.time = unit.convertToSeconds(preTime);
        }
    }

    private enum TimeUnit {

        SECONDS("s"),
        MINUTES("m"),
        HOURS("h"),
        DAYS("d");

        private String name;

        TimeUnit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Optional<TimeUnit> getByName(String name) {
            for (TimeUnit unit : values()) {
                if (unit.getName().equals(name)) return Optional.of(unit);
            }
            return Optional.empty();
        }

        public int convertToSeconds(int amount) {
            switch (this) {
                case SECONDS:
                    return amount;
                case MINUTES:
                    return TimeUtils.minutesToSeconds(amount);
                case HOURS:
                    return TimeUtils.hoursToSeconds(amount);
                case DAYS:
                    return TimeUtils.daysToSeconds(amount);
            }
            return 0;
        }

    }

}

