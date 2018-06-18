package net.reflxction.impuritybot.core.listeners;

import net.dv8tion.jda.core.entities.Game;
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
                setGame(Game.GameType.WATCHING, "Lith losing to a steveW || -help");
                break;
            case 1:
                setGame(Game.GameType.DEFAULT, "with Dani and MK4 || -help");
                break;
            case 2:
                setGame(Game.GameType.WATCHING, "Technoblade's bad videos || -help");
                break;
            case 3:
                setGame(Game.GameType.LISTENING, "Kyle's wise words || -help");
                break;
            case 4:
                setGame(Game.GameType.WATCHING, "anime with MK4 and D2G || -help");
                break;
            case 5:
                setGame(Game.GameType.DEFAULT, "Skyclash with Ref || -help");
                break;
            case 6:
                setGame(Game.GameType.DEFAULT, "TKR with Yrism || -help");
                break;
            case 7:
                setGame(Game.GameType.WATCHING, "the loss of Thommie12 in duels || -help");
                break;
            case 8:
                setGame(Game.GameType.WATCHING, "Thommie's road to 100 winstreaks || -help");
        }

    }
}
