package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.data.DataManager;


public class Filter extends ListenerAdapter {

    private String[] filters = {"fuck", "bitch", "cunt", "rape", "dick", "ass", "asshole", "dickhead", "ez", "ezy", "nub", "noob", "cancer"};

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!filterOn() || !containsFilteredContent(event.getMessage())) return;
        if (!event.getMessage().getContentRaw().startsWith("-")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Your content is deleted because it contains inappropriate, NFSW, or offensive content(s)").queue();
            return;
        }
        event.getChannel().sendMessage("Please do not send an inappropriate, NFSW, or offensive content(s)").queue();
    }

    public boolean containsFilteredContent(Message message) {
        String[] args = message.getContentRaw().split("\\s+");
        boolean containsFilteredContent = false;
        for (String string : args) {
            for (String filter : filters) {
                if (string.equalsIgnoreCase(filter)) containsFilteredContent = true;
            }
        }
        return containsFilteredContent;
    }

    public String[] getFilters() {
        return filters;
    }

    public boolean filterOn() {
        return ImpurityBot.getBot().getConfig().getBoolean("filter");
    }

    public void setFilterStatus(boolean isOn) {
        ImpurityBot.getBot().getConfig().set("filter", isOn);
        new DataManager(ImpurityBot.getBot()).saveFile(ImpurityBot.getBot().getConfig(), "config");
    }

}
