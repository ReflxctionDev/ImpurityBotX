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
package net.reflxction.impuritybot.utils.lang;

/**
 * Time utility, used to convert time between different units
 *
 * @author Reflxction
 */
public class TimeUtils {

    /**
     * Converts ticks to second
     *
     * @param ticks Ticks to convert
     * @return Ticks value in seconds
     */
    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    /**
     * Convert ticks to minutes
     *
     * @param ticks Ticks to convert
     * @return Ticks value in minutes
     */
    public static int ticksToMinutes(int ticks) {
        return ticksToSeconds(ticks) / 60;
    }

    /**
     * Converts ticks to hours
     *
     * @param ticks Ticks to convert
     * @return Ticks value in hours
     */
    public static int ticksToHours(int ticks) {
        return ticksToMinutes(ticks) / 60;
    }

    /**
     * Converts seconds to minutes
     *
     * @param seconds Seconds to convert
     * @return Seconds value in minutes
     */
    public static int secondsToMinutes(int seconds) {
        return seconds / 60;
    }

    /**
     * Converts seconds to hours
     *
     * @param seconds Seconds to convert
     * @return Seconds value in hours
     */
    public static int secondsToHours(int seconds) {
        return secondsToMinutes(seconds) / 60;
    }

    /**
     * Converts seconds to days
     *
     * @param seconds Seconds to convert
     * @return Seconds value in days
     */
    public static int secondsToDays(int seconds) {
        return seconds / 86400;
    }

    /**
     * Converts minutes to seconds
     *
     * @param minutes Minutes to convert
     * @return Minutes value, in seconds
     */
    public static int minutesToSeconds(int minutes) {
        return minutes * 60;
    }

    /**
     * Converts hours to seconds
     *
     * @param hours Hours to convert
     * @return Hours value in seconds
     */
    public static int hoursToSeconds(int hours) {
        return hours * 3600;
    }

    /**
     * Converts days to seconds
     *
     * @param days Days to convert
     * @return Days value in seconds
     */
    public static int daysToSeconds(int days) {
        int hours = days * 24;
        return hoursToSeconds(hours);
    }

    /**
     * Converts seconds to ticks
     * @param seconds Seconds to convert
     * @return Seconds value in ticks
     */
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }
}
