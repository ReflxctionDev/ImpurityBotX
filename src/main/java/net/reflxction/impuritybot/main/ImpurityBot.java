/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
 * * Copyright 2017-2018 github.com/BrokenEarthDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.impuritybot.main;

import com.google.common.eventbus.EventBus;
import me.brokenearth.core.container.Container;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.commands.fun.exclusive.Rate;
import net.reflxction.impuritybot.commands.hypixel.SkyWarsC;
import net.reflxction.impuritybot.core.cache.CacheHandler;
import net.reflxction.impuritybot.core.cache.ProfileAdapterCache;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.RegistryBuilder;
import net.reflxction.impuritybot.core.listeners.*;
import net.reflxction.impuritybot.core.loggers.Logger;
import net.reflxction.impuritybot.core.others.BotConfig;
import net.reflxction.impuritybot.levels.MessageListener;
import net.reflxction.impuritybot.logs.user.UserWarnLoggers;
import net.reflxction.impuritybot.utils.data.DataManager;
import net.reflxction.impuritybot.utils.data.WarningsManager;
import net.reflxction.impuritybot.utils.lang.TimeUtils;
import net.reflxction.impuritybot.utils.tps.TpsDelay;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImpurityBot extends JavaPlugin {

    public static ImpurityBot bot;
    private static JDA japi;
    private static boolean enabled = false;

    public static final EventBus EVENT_BUS = new EventBus();

    private static CacheHandler cacheHandler = new CacheHandler();

    private static List<ProfileAdapterCache> caches = new ArrayList<>();

    private File igns = new File(getDataFolder(), "igns.yml");
    private FileConfiguration ignsfile = YamlConfiguration.loadConfiguration(igns);

    private File credits = new File(getDataFolder(), "credits.yml");
    private FileConfiguration creditsfile = YamlConfiguration.loadConfiguration(credits);

    private File exp = new File(getDataFolder(), "exp.yml");
    private FileConfiguration expfile = YamlConfiguration.loadConfiguration(exp);

    private File warnings = new File(getDataFolder(), "warnings.yml");
    private FileConfiguration warningsfile = YamlConfiguration.loadConfiguration(warnings);

    private File polls = new File(getDataFolder(), "polls.yml");
    private FileConfiguration pollsfile = YamlConfiguration.loadConfiguration(polls);

    private File points = new File(getDataFolder(), "points.yml");
    private FileConfiguration pointsfile = YamlConfiguration.loadConfiguration(points);

    private File rules = new File(getDataFolder(), "rules.png");
    private File ranks = new File(getDataFolder(), "ranks.png");
    private File punishments = new File(getDataFolder(), "punishments.png");

    public static ImpurityBot getBot() {
        return bot;
    }

    public void enable() {
        bot = this;
        try {
            registerListeners();
            register();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        enabled = true;
    }

    public static void disable() {
        enabled = false;
        try {
            japi.shutdown();
        } catch (Exception ex) {
            System.out.println("Couldn't shut down the bot. Please contact the developer and include the following stack-trace:");
            ex.printStackTrace();
        }
    }

    public static JDA getJDA() {
        return japi;
    }

    /**
     * <@&387535698909200384> - Head Officer
     * <@&367318849978105857> - Admin
     * <@&364809414743687169> - Officer
     * <@&378902256902209539> - Helper
     * <@&395928472473698304> - Trusted
     */
    public boolean isBotEnabled() {
        return enabled;
    }

    @Override
    public void onEnable(){
        bot = this;
        UserWarnLoggers.wu = new WarningsManager(this);
        WarningsManager.loggers = new UserWarnLoggers(this);
        enable();
        japi.getPresence().setGame(Game.listening("Shifty's wise words"));
        GameManager gm = new GameManager();
        DelayManager dm = new DelayManager();
        TpsDelay tps = new TpsDelay();
        MuteManager mute = new MuteManager();
        //TODO
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, gm::manageGames, 12000, 12000);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (int i = 0; i < getImpurityGuild().getMembers().size(); i++) {
                User u = getImpurityGuild().getMembers().get(i).getUser();
                dm.manageDelay(u);
            }
            tps.start();
            mute.updateMuteTime();
        }, 80, 80);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, tps::start, TimeUtils.secondsToTicks(90), TimeUtils.secondsToTicks(90));
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, cacheHandler::updateCache, TimeUtils.secondsToTicks(15), TimeUtils.secondsToTicks(15));
        final DataManager data = new DataManager(this);
        saveDefaultConfig();
        data.loadFiles();

    }

    @Override
    public void onDisable() {
        disable();
        bot = null;
    }

    public FileConfiguration getIgnsFile() {
        return ignsfile;
    }

    public FileConfiguration getCreditsFile() {
        return creditsfile;
    }

    public FileConfiguration getExpFile() {
        return expfile;
    }

    public FileConfiguration getWarningsFile() {
        return warningsfile;
    }

    public FileConfiguration getPollsFile() {
        return pollsfile;
    }

    public FileConfiguration getPointsFile() {
        return pointsfile;
    }

    public File getRulesP() {
        return rules;
    }

    public File getRanksP() {
        return ranks;
    }

    public File getPunishmentP() {
        return punishments;
    }

    public static Guild getImpurityGuild() {
        return getJDA().getGuildById("363721897743089668");
    }

    private void registerListeners() throws LoginException {
        japi = new JDABuilder(AccountType.BOT)
                .setToken(BotConfig.TOKEN)
                .addEventListener(new MessageListener(), new SkyWarsC(), new Welcome(), new Engine(), new Rate(),
                        new TableFlip(), new PollReactions(bot), new CleanChatFilter()).buildAsync();

    }

    private void register() {
        final RegistryBuilder registry = new RegistryBuilder(getJDA());
        for (AbstractCommand abstractCommand : Register.getAbstractCommands()) {
            registry.registerCommand(abstractCommand);
        }
        for (Logger logger : Register.getLoggers()) {
            registry.registerLogger(logger);
        }
    }

    public static List<ProfileAdapterCache> getCurrentCache() {
        return caches.isEmpty() ? cacheHandler.updateCache() : caches;
    }

}
