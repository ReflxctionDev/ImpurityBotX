/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
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

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageType;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.Random;

public class EmbedFactory {


    private EmbedBuilder builder;

    public EmbedFactory(EmbedBuilder builder) {
        this.builder = builder;
    }



    public EmbedFactory setAuthor(String author, String url, String iconUrl) {
        builder.setAuthor(author, url, iconUrl);
        return this;
    }

    public EmbedFactory setColor(Color color) {
        builder.setColor(color);
        return this;
    }


    public EmbedFactory setDescription(String header) {
        builder.setDescription(header);
        return this;
    }

    public EmbedFactory setFooter(String text, String url) {
        builder.setFooter(text, url);
        return this;
    }

    public EmbedFactory addField(String name, String value, boolean inline) {
        builder.addField(new MessageEmbed.Field(name, value, inline));
        return this;
    }

    public EmbedFactory setImage(String url) {
        builder.setImage(url);
        return this;
    }

    public EmbedFactory setThumbnail(String url) {
        builder.setThumbnail(url);
        return this;
    }


    public EmbedFactory setTimestamp(TemporalAccessor accessor) {
        builder.setTimestamp(accessor);
        return this;
    }

    public EmbedFactory setTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    public EmbedFactory addBlankField() {
        builder.addBlankField(false);
        return this;
    }

    public EmbedFactory setRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        Color color = new Color(r, g, b);
        builder.setColor(color);
        return this;
    }

    public EmbedFactory addField(String name, String value) {
        builder.addField(name, value, false);
        return this;
    }

    public EmbedBuilder build() {
        return builder;
    }

}
