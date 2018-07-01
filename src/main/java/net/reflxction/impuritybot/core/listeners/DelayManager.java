package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.DataManager;

public class DelayManager implements IDelayManager {

    private ImpurityBot bot = ImpurityBot.getPlugin(ImpurityBot.class);

    private DataManager du = new DataManager(bot);

    @Override
    public void manageDelay(User u) {
        int remainingTime = bot.getCreditsFile().getInt("Credits." + u.getId() + ".TimeLeft");
        if (remainingTime != 0) {
            bot.getCreditsFile().set("Credits." + u.getId() + ".TimeLeft", remainingTime - 10);
            du.saveCreditsFile();
        }
        int muteTime = bot.getCreditsFile().getInt("Credits." + u.getId() + ".Mute");
        if (muteTime != 0) {
            bot.getCreditsFile().set("Credits." + u.getId() + ".Mute", muteTime  - 10);
            du.saveCreditsFile();
        }
    }

    public void noDailyForYou(User u) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".TimeLeft", 86400);
        du.saveCreditsFile();
    }


    public void mute(User u) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".Mute", 600);
        du.saveCreditsFile();
    }
}

