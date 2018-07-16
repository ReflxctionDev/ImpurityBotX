package net.reflxction.impuritybot.core.loggers;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.main.ImpurityBot;

import java.awt.*;

/*
 * Created by Reflxction, on 01/28/18.
 */
public abstract class Logger extends ListenerAdapter {

    protected static final Color L_RED = new Color(255, 97, 94);

    protected static final Color L_GREEN = new Color(136, 255, 131);

    protected static final Color L_BLUE = new Color(168, 201, 255);

    protected static final Color YELLOW = new Color(255, 255, 0);

    public TextChannel getLogs() {
        return ImpurityBot.getImpurityGuild().getTextChannelById("407256687310012447");
    }

    protected TextChannel getWarningLogs() {
        return ImpurityBot.getImpurityGuild().getTextChannelById("407968054165635083");
    }

}
