package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.DataManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter extends ListenerAdapter {

    private String[] filters = {
            "fuck",
            "bitch",
            "cunt",
            "rape",
            "dick",
            "ass",
            "asshole",
            "dickhead",
            "ez",
            "ezy",
            "nub",
            "noob",
            "cancer"
    };

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!filterOn() || !containsFilteredContent(event.getMessage())) return;
        PrivateChannel pm = event.getAuthor().openPrivateChannel().complete();
        event.getMessage().delete().queue();
        pm.sendMessage("Your content has been deleted because it contains inappropriate, NFSW, or offensive content. Please watch your language next time to avoid further punishments. Thanks").queue();
    }

    /*private boolean containsFilteredContent(Message message) {
        String[] args = message.getContentRaw().split("\\s+");
        boolean containsFilteredContent = false;
        for (String string : args) {
            for (String filter : filters) {
                if (string.equalsIgnoreCase(filter)) {
                    containsFilteredContent = true;
                    break;
                }
            }
        }
        return containsFilteredContent;
    }*/

    private boolean containsFilteredContent(Message message) {
        for (String swear : filters) {
            String pattern = "\\b" + swear + "\\b";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(message.getContentRaw());
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    public boolean filterOn() {
        return ImpurityBot.getBot().getConfig().getBoolean("filter");
    }

    public void setFilterStatus(boolean isOn) {
        ImpurityBot.getBot().getConfig().set("filter", isOn);
        new DataManager(ImpurityBot.getBot()).saveFile(ImpurityBot.getBot().getConfig(), "config");
    }

}
