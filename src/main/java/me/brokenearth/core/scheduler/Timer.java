package me.brokenearth.core.scheduler;

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
 * @author BrokenEarth // BrokenEarthDev
 * @version 1.0
 * @see Scheduler
 */
public abstract class Timer {
    /**
     * The method that is called when the timer fulfills
     */
    public abstract void run();
    /**
     * Original timer instance
     */
    private java.util.Timer timer = new java.util.Timer();
    /**
     * Defines if the timer is cancelled or not
     */
    private boolean isCancelled;
    /**
     * Schedules the timer until the time (in millis) is over and then calls the run method
     * @param millis time must be in milliseconds
     */
    public void schedule(final int millis) {
        final Timer timer = this;
        this.timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                if (isCancelled) return;
                timer.run();
            }
        }, millis);
    }
    /**
     * Schedules the timer until the time (in millis) is over and then calls the run method over
     * and over again
     * @param millis time mute be in milliseconds
     */
    public void runEvery(final int millis) {
        final Timer timer = this;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isCancelled) return;
                timer.run();
                runEvery(millis);
            }
        }, millis);
    }
    /**
     * Cancels the timer
     */
    public void cancel() {
        isCancelled = true;
        timer.cancel();
    }
    /**
     * @return whether or not the timer is cancelled
     */
    public boolean isCancelled() {
        return isCancelled;
    }

}