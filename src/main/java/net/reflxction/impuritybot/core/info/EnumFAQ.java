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
 * Class which contains frequently asked questions
 */
public enum EnumFAQ implements IEnumInfo {

    F1("How to get promoted to staff?", "As we have mentioned in the rules, members are not allowed to ask for promotions from staff, and doing so will lower your chances of being promoted. However, to help the guild, consider joining one of our different teams, namely the Application Team, the Design Team, the GvG team, the Helper team, and the Events team. You will have a chance to be promoted to officer based on your contribution to these teams, how active you are, and your maturity."),
    F2("How do I seek help if I have a problem with the guild or with a guild mate?", "As we have mentioned in the rules, members are not allowed to ask for promotions from staff, and doing so will lower your chances of being promoted. However, to help the guild, consider joining one of our different teams, namely the Application Team, the Design Team, the GvG team, the Helper team, and the Events team. You will have a chance to be promoted to officer based on your contribution to these teams, how active you are, and your maturity."),
    F3("What should I do if I find out that another member of Impurity is hacking?", "There are many ways to find help. If you have a problem regarding the discord or the guild itself, you are welcome to private message one of the officers in game or seek help in <#363721897743089671>. On the other hand, if someone is bothering you, report him/her immediately. We aim to bring the best possible experience for everyone so you should DM any Helper+ on our discord or private message an in game officer. Remember that we genuinely care for every member of the guild and you are always free to approach us with any problems or questions you have."),
    F4("What do I do if another member of Impurity is being toxic/rude?", "If they are on the discord, tag a helper or above to get help. If you see them being toxic or rude in game however, take a screenshot immediately and send the evidence to an admin/officer."),
    F5("What if my friend wants to join the guild? Can he/she get an exception?", "Unfortunately, your friend may or may not be an exception and will have to apply on the forums. Give them a link to our guild thread and ask them to apply. If they are denied because they don't meet requirements, they can duel <@184684243417432064> for an exception."),
    F6("I have to be offline for more than 3 days. What should i do?", "You should tag an admin/officer to notify them as soon as possible with reasoning. If you get kicked even after you had already notified a staff member, DM an officer or above and send the proof you told a staff member you will be offline and we will re-invite you back to the guild promptly.");

    private String question, answer;

    EnumFAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public EmbedBuilder getAsEmbed() {
        return new EmbedFactory(new EmbedBuilder())
                .addField(getQuestion(), getAnswer())
                .setRandomColor()
                .build();
    }

    public static File getImage() {
        return EnumImages.FAQ.getFile();
    }

}
