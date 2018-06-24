package me.brokenearth.core.scheduler;

import com.sun.istack.internal.NotNull;

import java.util.TimerTask;

/**
 * Copyright 2018 github.com/BrokenEarthDev
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 * @author BrokenEarth // BrokenEarthDeva
 * @version 1.0
 * @see Timer
 */
public final class Scheduler {
    /**
     * Original timer instance
     */
    private java.util.Timer timer1 = new java.util.Timer();
    /**
     * Schedules the timer and calls when time (in millis) is done
     * @param timer the timer class
     * @param millis time must be in milliseconds
     */
    public void schedule(@NotNull final Timer timer, int millis) {
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.run();
            }
        }, millis);
    }
    /**
     * Schedules the timer until the time (in millis) is over and then calls the run method over
     * and over again
     * @param timer the timer class
     * @param millis time mute be in milliseconds
     */
    public void runEvery(@NotNull final Timer timer, final int millis) {
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.run();
                runEvery(timer, millis);
            }
        }, millis);
    }
    /**
     * Cancels the timer of the given class
     * @param timer the timer class
     */
    public void cancel(@NotNull Timer timer) {
        timer.cancel();
        timer1.cancel();
    }
}
