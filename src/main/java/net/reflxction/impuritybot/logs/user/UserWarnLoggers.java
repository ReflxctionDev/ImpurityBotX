package net.reflxction.impuritybot.logs.user;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.core.others.EmbedFactory;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.WarningsManager;

/**
 * Created by Reflxction, on 01/30/18.
 */
public class UserWarnLoggers extends Logger {

    private static ImpurityBot bot;

    public UserWarnLoggers(ImpurityBot bot) {
        this.bot = bot;
    }

    public static WarningsManager wu = new WarningsManager(bot);

    public void logGivenWarn(final User u, final User warner, String reason) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(L_RED)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("**" + warner.getName() + "** has warned **" + u.getName() + "**. Reason: **" + reason + "**. Total warnings: **" + wu.getWarnings(u) + "**")
                .setThumbnail(u.getAvatarUrl())
                .build();
        TextChannel logs = ImpurityBot.getImpurityGuild().getTextChannelById("407968054165635083");
        logs.sendMessage(builder.build()).queue();
    }

    public void logRemovedWarn(final User u, final User remover, int amount) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(YELLOW)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("**" + remover.getName() + "** has removed **" + amount + "** warning(s) from **" + u.getName() + "**. Total warnings: **" + wu.getWarnings(u) + "**")
                .setThumbnail(u.getAvatarUrl())
                .build();
        TextChannel logs = ImpurityBot.getImpurityGuild().getTextChannelById("407968054165635083");
        logs.sendMessage(builder.build()).queue();
    }

    public void logClearedWarnings(final User u, final User remover) {
        EmbedBuilder builder = new EmbedFactory(new EmbedBuilder())
                .setColor(YELLOW)
                .setAuthor(u.getName(), null, u.getAvatarUrl())
                .setDescription("**" + remover.getName() + "** has cleared all the warnings of **" + u.getName() + "**. Total warnings: **" + wu.getWarnings(u) + "**")
                .setThumbnail(u.getAvatarUrl())
                .build();
        TextChannel logs = ImpurityBot.getImpurityGuild().getTextChannelById("407968054165635083");
        logs.sendMessage(builder.build()).queue();
    }

}
