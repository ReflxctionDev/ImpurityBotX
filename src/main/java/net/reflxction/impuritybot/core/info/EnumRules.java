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
package net.reflxction.impuritybot.core.info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.reflxction.impuritybot.core.others.EmbedFactory;

import java.io.File;

/**
 * Class which has all the rules of the Discord
 */
public enum EnumRules implements IEnumInfo {

    R1("1. Follow the rules of the Hypixel Network", "If you intend to become a part of Impurity and of the Hypixel community, keep in mind to follow the Hypixel rules. This ensures that everyone isn’t getting bothered by someone else and creates a friendly community."),
    R2("2. Do not spam in chat or on the discord", "Please keep in mind that the guild chat is intended to be used as a quick communication tool between the online Impurity members, and thus everyone would love it to be clean. Furthermore, remember that the Discord channel is another way to communicate with all the Discord members, including our allies. What we try to do is to give a good impression, so help us to do so by not littering the chat with excessive, similar messages. If you find that you’re sending 5 or more messages in the discord chat and no one is responding to you, stop or risk confusion and you getting warned."),
    R3("3. No NSFW or sexual/inappropriate photos/videos/links", "Any NSFW links are strictly forbidden under any circumstances. Please refrain from posting any inappropriate photographs or links. Doing so is punishable and keep in mind that there may be kids here, whether or not they are a guild member or an ally/friend. This also includes sexual topics and phrases, discrimination towards another gender or race, etc."),
    R4("4. Keep discussions and conversations in English", "We do understand that your native (first) language may not be English, and you'd like to speak your language with your fellows, however remember that this is a public Discord, thus all discussions are to be appropriate and understandable to all of our members. If you'd like to discuss in another language, take it to private messages or create a group with your maties."),
    R5("5. Be active", "As a guild, we need to make sure that everyone is active. If you need to be inactive for over 3 days in game, then please inform a staff member or you will be kicked for inactivity."),
    R6("6. No advertising", "Any links that do not belong to Impurity or refer to it are forbidden. This doesn't mean you can go to all of our members one by one messaging them, as this will also be considered as spam. Please avoid posting any links or websites that are unrelated to Impurity."),
    R7("7. No asking for promotions", "If you intend to contribute to our guild, then feel free to apply for available positions like teams. Keep in mind that helpers/officers will be selected based on one’s performance in our teams and their maturity shown in chat. Please avoid asking for a promotion to a higher position as this will tremendously lower your chances of getting accepted."),
    R8("8. Use the appropriate channel", "In the intent of making the Discord server more organized, we've made multiple channels each one with its own purpose. Before you talk, please make sure you're using the right channel to avoid getting warned and/or punished in the future. A good example would be using bot commands in <#363721897743089671>."),
    R9("9. Do not attempt to tag @everyone", "The @everyone is a nice feature that Discord provides, however it's easily abused, and many people get annoyed by it. If you are to use it, do request a staff for permission to use it beforehand for a valid reason. Any attempts to tag @everyone are punishable, so do not play around with it."),
    R10("10. Use common sense", "Before you do anything, make sure it's lawful. Failing to follow any of the rules is punishable, however, it's not an excuse either to say \"it's not mentioned in the rules, so it's allowed\" to avoid the punishment. Common sense is to be used by everyone. Keep in mind that rules are not limited to what we mention and are subject to change as per staff wish."),
    R11("11. Respect other players/members", "Any form of harassment, toxicity, insults directed towards other people, suicide encouragement, and excessive/inappropriate tagging are not allowed under any circumstances and are punishable."),
    R12("12. No alternative accounts", "Using alternative accounts is not accepted under any circumstances, unless granted by an elder staff member. Using them to evade mutes or bans, will result in the punishment getting worse."),
    R13("13. Have fun!", "What's the point of being in the guild if you are not having fun? We may sound a bit strict, however this is all to make sure that each one in the guild is having fun and not being annoyed by someone else. Remember to respect everyone, and you'll have a great time here!\n");

    private String name;
    private String description;

    EnumRules(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;

    }

    public EmbedBuilder getAsEmbed() {
        return new EmbedFactory(new EmbedBuilder())
                .addField(getName(), getDescription())
                .setRandomColor()
                .build();
    }

    public static File getImage() {
        return EnumImages.RULES.getFile();
    }

}
