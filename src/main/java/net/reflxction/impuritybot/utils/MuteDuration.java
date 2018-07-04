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

    // Time, before getting converted to seconds
    private int preTime;

    /**
     * Constructor which uses regex to parse the mute duration
     *
     * @param parse String parse
     * @throws DurationParseException If the given duration couldn't be parsed
     */
    public MuteDuration(String parse) throws DurationParseException {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(parse);
        while (m.find()) {
            preTime = Integer.parseInt(m.group());
            this.unit = TimeUnit.getByName(parse.replace(m.group(), "")).get();
            this.time = unit.convertToSeconds(preTime);
        }
    }

    /**
     * Time of the mute in seconds
     *
     * @return Time of the mute in seconds
     */
    public int getTime() {
        return time;
    }

    /**
     * @return Human-readable text (e.g "5 minute(s)")
     */
    public String getNiceName() {
        return preTime + " " + unit.getNiceName();
    }

    private enum TimeUnit {

        SECONDS("s", "second(s)"),
        MINUTES("m", "minute(s)"),
        HOURS("h", "hour(s)"),
        DAYS("d", "day(s)");

        private String name;
        private String niceName;

        TimeUnit(String name, String niceName) {
            this.name = name;
            this.niceName = niceName;
        }

        public String getName() {
            return name;
        }

        public String getNiceName() {
            return niceName;
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

    public class DurationParseException extends Exception {

        private String message;

        DurationParseException(String message) {
            super(message);
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

}

