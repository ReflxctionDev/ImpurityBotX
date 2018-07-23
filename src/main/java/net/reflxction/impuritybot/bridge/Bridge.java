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
package net.reflxction.impuritybot.bridge;

import net.dv8tion.jda.core.entities.Channel;

/**
 * Bridge object which every bridge has
 *
 * @author Reflxction
 */
public class Bridge {

    private Channel channel1, channel2;
    private int id;

    public Bridge(Channel channel1, Channel channel2, int id) {
        this.channel1 = channel1;
        this.channel2 = channel2;
        this.id = id;
    }

    public Channel getChannel1() {
        return channel1;
    }

    public Channel getChannel2() {
        return channel2;
    }

    public int getId() {
        return id;
    }
}
