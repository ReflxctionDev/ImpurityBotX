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

package net.reflxction.impuritybot.core.events;

import com.google.common.base.Preconditions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Base Event class that all other events are derived from
 */
public class Event {
    @Retention(value = RUNTIME)
    @Target(value = TYPE)
    public @interface HasResult {
    }

    public enum Result {
        DENY,
        DEFAULT,
        ALLOW
    }

    private boolean isCanceled = false;
    private Result result = Result.DEFAULT;
    private static ListenerList listeners = new ListenerList();
    private EventPriority phase = null;

    public Event() {
        setup();
    }

    /**
     * Determine if this function is cancelable at all.
     *
     * @return If access to setCanceled should be allowed
     * <p>
     * Note:
     * Events with the Cancelable annotation will have this method automatically added to return true.
     */
    public boolean isCancelable() {
        return false;
    }

    /**
     * Determine if this event is canceled and should stop executing.
     *
     * @return The current canceled state
     */
    public boolean isCanceled() {
        return isCanceled;
    }

    /**
     * Sets the cancel state of this event. Note, not all events are cancelable, and any attempt to
     * invoke this method on an event that is not cancelable (as determined by {@link #isCancelable}
     * will result in an {@link UnsupportedOperationException}.
     * <p>
     * The functionality of setting the canceled state is defined on a per-event bases.
     *
     * @param cancel The new canceled value
     */
    public void setCanceled(boolean cancel) {
        if (!isCancelable()) {
            throw new UnsupportedOperationException(
                    "Attempted to call Event#setCanceled() on a non-cancelable event of type: "
                            + this.getClass().getCanonicalName()
            );
        }
        isCanceled = cancel;
    }

    /**
     * Determines if this event expects a significant result value.
     * <p>
     * Note:
     * Events with the HasResult annotation will have this method automatically added to return true.
     */
    public boolean hasResult() {
        return false;
    }

    /**
     * Returns the value set as the result of this event
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the result value for this event, not all events can have a result set, and any attempt to
     * set a result for a event that isn't expecting it will result in a IllegalArgumentException.
     * <p>
     * The functionality of setting the result is defined on a per-event bases.
     *
     * @param value The new result
     */
    public void setResult(Result value) {
        result = value;
    }

    /**
     * Called by the base constructor, this is used by ASM generated
     * event classes to setup various functionality such as the listener list.
     */
    protected void setup() {
    }

    /**
     * Returns a ListenerList object that contains all listeners
     * that are registered to this event.
     *
     * @return Listener List
     */
    public ListenerList getListenerList() {
        return listeners;
    }

    public EventPriority getPhase() {
        return this.phase;
    }

    public void setPhase(EventPriority value) {
        Preconditions.checkNotNull(value, "setPhase argument must not be null");
        int prev = phase == null ? -1 : phase.ordinal();
        Preconditions.checkArgument(prev < value.ordinal(), "Attempted to set event phase to %s when already %s", value, phase);
        phase = value;
    }
}
