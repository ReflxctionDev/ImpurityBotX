package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.GuildUtils;
import net.reflxction.impuritybot.utils.data.DataManager;

public class DelayManager implements IDelayManager {

    private ImpurityBot bot = ImpurityBot.getPlugin(ImpurityBot.class);

    private DataManager du = new DataManager(bot);

    @Override
    public void manageDelay(User u) {
        int remainingTime = bot.getCreditsFile().getInt("Credits." + u.getId() + ".TimeLeft");
        if (remainingTime != 0) {
            bot.getCreditsFile().set("Credits." + u.getId() + ".TimeLeft", remainingTime - 4);
            du.saveCreditsFile();
        }
        int muteTime = bot.getCreditsFile().getInt("Credits." + u.getId() + ".Mute");
        if (muteTime != 0) {
            bot.getCreditsFile().set("Credits." + u.getId() + ".Mute", muteTime  - 4);
            du.saveCreditsFile();
            ensureMute(u);
        } else {
            Role muted = GuildUtils.guild().getRoleById("430447415515152384");
            if (GuildUtils.guild().getMember(u).getRoles().contains(muted)) {
                GuildUtils.controller().removeSingleRoleFromMember(GuildUtils.guild().getMember(u), muted).queue();
            }
        }
    }

    public void noDailyForYou(User u) {
        bot.getCreditsFile().set("Credits." + u.getId() + ".TimeLeft", 86400);
        du.saveCreditsFile();
    }


    public void mute(User u) {
        ensureMute(u);
        bot.getCreditsFile().set("Credits." + u.getId() + ".Mute", 600);
        du.saveCreditsFile();
    }

    private void ensureMute(User u) {
        GuildUtils.controller().addSingleRoleToMember(GuildUtils.guild().getMember(u), GuildUtils.roleById("430447415515152384")).queue();
    }
}

