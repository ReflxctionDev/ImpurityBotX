package net.reflxction.impuritybot.core.listeners.discord_events;

import net.dv8tion.jda.core.entities.Game;

/**
 * Created by Reflxction, on 01/28/18.
 */
public interface IPlayingStateManager {

    void setGame(Game.GameType game, String g);

    void manageGames();

}
