package net.reflxction.impuritybot.core.listeners.discord;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.reflxction.impuritybot.core.others.IAccess;
import org.bukkit.Bukkit;

public class Engine implements EventListener, IAccess {

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            Bukkit.getLogger().info("[Impure Bot] Client logged in");
        }
    }
}
