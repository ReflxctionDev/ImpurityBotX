package net.reflxction.impuritybot.core.listeners.discord_events;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.reflxction.impuritybot.main.ImpurityBot;
import net.reflxction.impuritybot.utils.lang.NumberUtils;

/**
 * Created by Reflxction, on 01/28/18.
 */
public class GameManager implements IPlayingStateManager {

    @Override
    public void setGame(Game.GameType game, String g) {
        ImpurityBot.getJDA().getPresence().setGame(Game.of(game, g));
    }

    @Override
    public void manageGames() {
        final NumberUtils nu = new NumberUtils();
        int r = nu.randomBetween(0, 8);
        switch (r) {
            case 0:
                setGame(Game.GameType.WATCHING, "Scindra die to a steve || -help");
                break;
            case 1:
                setGame(Game.GameType.DEFAULT, "with SkyWars with daddy Daresome || -help");
                break;
            case 2:
                setGame(Game.GameType.WATCHING, "Impurity's bad videos || -help");
                break;
            case 3:
                setGame(Game.GameType.LISTENING, "Loki's wise words || -help");
                break;
            case 4:
                setGame(Game.GameType.WATCHING, "anime with Meth || -help");
                break;
            case 5:
                setGame(Game.GameType.DEFAULT, "the Pit with Reflxction || -help");
                break;
            case 6:
                setGame(Game.GameType.DEFAULT, "Bedwars with BrokenEarth || -help");
                break;
            case 7:
                setGame(Game.GameType.WATCHING, "people burn with FiteMeNerd || -help");
                break;
            case 8:
                setGame(GameType.WATCHING, "Nikky fall in the void || -help");
                break;
        }

    }
}
