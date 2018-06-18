package net.reflxction.impuritybot.commands.admin.news;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.awt.*;

public class WelcomeManager extends AbstractCommand {
    @Override
    public String getCommand() {
        return "readio";
    }

    private ImpurityBot bot;

    public WelcomeManager(ImpurityBot bot) {
        this.bot = (bot == null) ? ImpurityBot.getPlugin(ImpurityBot.class) : bot;
    }

    @Override
    public void process(JDA j, Guild g, Message m, MessageChannel c, User u, String[] args) {
        m.delete().queue();
        if (g.getMember(u).hasPermission(Permission.MESSAGE_MANAGE)) {
            if (g.getCategoryById("380122495728222218").getChannels().contains(c)) {
                /*
                 * Rules
                 */
                c.sendFile(bot.getRulesP()).queue();
                //TODO Rule 1
                EmbedBuilder rule1 = asEmbed("Rule 1: Follow the Hypixel rules", "If you intend to be a member of the Impurity guild & community, you must keep in mind that following the Hypixel rules is an essential if you don't want to get into any problems");
                c.sendMessage(rule1.build()).queue();
                //TODO Rule 2
                EmbedBuilder rule2 = asEmbed("Rule 2: Do not spam the guild chat or the Discord channel", "Please keep in mind that the guild chat is intended to be used as a quick communication tool between the online Impurity members, thus everyone would love it to be clean. Beside this, keep in mind that the Discord channel is another way to communicate with *all* the Discord members, including the allies. What we try is giving a good impression, so help us to do so.");
                c.sendMessage(rule2.build()).queue();
                //TODO Rule 3
                EmbedBuilder rule3 = asEmbed("Rule 3: No NSFW, sexual, or inappropriate content or topics", "Any NSFW, sexual or inappropriate content or topics is forbidden under any circumstances. Doing so will get you punished. If you are to talk about them, then please do in the <#378892621424361472> channel. Otherwise, please avoid it.");
                c.sendMessage(rule3.build()).queue();
                //TODO Rule 4
                EmbedBuilder rule4 = asEmbed("Rule 4: Remind others that they need to be active at least once a week", "As a guild, we need to make sure that everyone is active. If you need to be inactive for a while, then please inform us at <#384275289997443072> to avoid being kicked.");
                c.sendMessage(rule4.build()).queue();
                //TODO Rule 5
                EmbedBuilder rule5 = asEmbed("Rule 5: No asking for promotions", "If you intend to contribute to our guild, then feel free to apply to the position you feel that fits you. Please avoid asking for a promotion to a higher position without applying and getting accepted.");
                c.sendMessage(rule5.build()).queue();
                //TODO Rule 6
                EmbedBuilder rule6 = asEmbed("Rule 6: Always use the correct channel", "In the intent of making the Discord server more organized, we've made multiple channels each one with its own purpose. Before you talk, please make sure you're using the right channel to avoid getting warned and/or punished in the future.");
                c.sendMessage(rule6.build()).queue();
                //TODO Rule 7
                EmbedBuilder rule7 = asEmbed("Rule 7: Do not tag @everyone without staff permission", "The `@everyone` is a nice feature that Discord provides, however it's easily abused, and many people get annoyed by it. If you are to use it, please do so in the right moment.");
                c.sendMessage(rule7.build()).queue();
                //TODO Rule 8
                EmbedBuilder rule8 = asEmbed("Rule 8: Always use common sense", "Before you do anything, make sure it's lawful. Failing to follow any of the rules is punishable, however, it's not an excuse either to say \"it's not mentioned in the rules, so it's allowed\" to avoid the punishment. Common sense is to be used by everyone. Keep in mind that rules are not limited to what we mention");
                c.sendMessage(rule8.build()).queue();
                //TODO Rule 9
                EmbedBuilder rule9 = asEmbed("Rule 9: No alternative accounts unless granted staff permission", "Using alternative accounts is not accepted under any circumstances, unless granted by an elder staff member. Using them to evade mutes or bans, will result in the punishment getting worse.");
                c.sendMessage(rule9.build()).queue();
                //TODO Rule 10
                EmbedBuilder rule10 = asEmbed("Rule 10: Have fun!", "What's the point of being in the guild if you are not having fun? We may sound a bit strict, however this is all to make sure that each one in the guild is having fun and not being annoyed by another one.");
                c.sendMessage(rule10.build()).queue();
                /*
                 * Ranks
                 */
                c.sendFile(bot.getRanksP()).queue();
                //TODO Guild Master
                c.sendMessage("**Staff ranks**").queue();
                EmbedBuilder rank1 = asEmbed("Guild Master", "The official guild master of Impurity. Only held by <@348284085971451904>");
                c.sendMessage(rank1.build()).queue();
                //TODO Head Admin
                EmbedBuilder rank2 = asEmbed("Head Admin", "Hand picked by the guild master, this role is the highest after guild master, responsible of managing all the other staff members");
                c.sendMessage(rank2.build()).queue();
                //TODO Admin
                EmbedBuilder rank4 = asEmbed("Admin", "Discord admins, help in managing the chat and moderating punishments, as well as improving the Discord");
                c.sendMessage(rank4.build()).queue();
                //TODO Guild Advisor
                EmbedBuilder rank3 = asEmbed("Guild Advisor", "Trusted members, who are known to be wise and make wise decisions.");
                c.sendMessage(rank3.build()).queue();
                //TODO Moderator
                EmbedBuilder rank5 = asEmbed("Moderator", "Discord moderators, help in keeping the chat clean and managing it");
                c.sendMessage(rank5.build()).queue();
                //TODO Developer
                EmbedBuilder rank6 = asEmbed("Developer", "People who work on creating guild-exclusive features, cosmetics, or goods");
                c.sendMessage(rank6.build()).queue();
                //TODO Officer
                EmbedBuilder rank7 = asEmbed("Officer", "Guild officers in NotThatPure and Impurity");
                c.sendMessage(rank7.build()).queue();
                //TODO Support
                EmbedBuilder rank8 = asEmbed("Support", "Discord helpers, who fill people's role requests and guide them");
                c.sendMessage(rank8.build()).queue();
                c.sendMessage("===========================================").queue();
                c.sendMessage("**Level ranks**").queue();
                //TODO Introvert
                EmbedBuilder rank9 = asEmbed("Farmer", "Discord level, from level 1 to 5. Gain levels and EXP by chatting");
                c.sendMessage(rank9.build()).queue();
                //TODO Normie
                EmbedBuilder rank10 = asEmbed("Page", "Discord level, from level 5+. Gain levels and EXP by chatting");
                c.sendMessage(rank10.build()).queue();
                //TODO Famous
                EmbedBuilder rank11 = asEmbed("Knight", "Discord level, from level 5 to 15. Gain levels and EXP by chatting");
                c.sendMessage(rank11.build()).queue();
                //TODO Star
                EmbedBuilder rank12 = asEmbed("Prince", "Discord level, from level 15+. Gain levels and EXP by chatting");
                c.sendMessage(rank12.build()).queue();
                c.sendMessage("===========================================").queue();
                c.sendMessage("**Guild teams ranks**").queue();
                //TODO YT Team
                EmbedBuilder rank13 = asEmbed("YouTube Team", "Members of the YouTube team of the guild. Apply on the forums");
                c.sendMessage(rank13.build()).queue();
                //TODO GvG Team
                EmbedBuilder rank14 = asEmbed("GvG Team", "People who represent the guild in GvGs (Guild vs Guild). Apply on the forums");
                c.sendMessage(rank14.build()).queue();
                //TODO Server devs
                EmbedBuilder rank15 = asEmbed("Server Developers", "Staff of our public server");
                c.sendMessage(rank15.build()).queue();
                c.sendMessage("===========================================").queue();
                c.sendMessage("**Hypixel ranks**").queue();
                /*
                 * Punishments
                 */
                c.sendFile(bot.getPunishmentP()).queue();
                c.sendMessage(p().build()).queue();
            }
        }
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Get the rules";
    }

    @Override
    public String getUsage() {
        return "-readio";
    }

    private EmbedBuilder asEmbed(String title, String value) {
        return new EmbedFactory(new EmbedBuilder())
                .setTitle(title)
                .setRandomColor()
                .addField("", value)
                .build();
    }

    private EmbedBuilder p() {
        return new EmbedFactory(new EmbedBuilder())
                .setDescription("Punishments you may face when not following the rules above:\n")
                .addField("Spam", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .addField("Swearing", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban\n", true)
                .addField("Not obeying higher people", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .addField("Not using the correct channel", "-2 warnings\n" +
                        "-mute(1 hour or more, most of the time)\n" +
                        "-kick(1 day - 1 week)\n" +
                        "-ban", true)
                .setColor(Color.RED)
                .setFooter("Accumulation of warnings may get you kicked, muted, demoted or even banned from our Discord server", null)
                .build();
    }
}
