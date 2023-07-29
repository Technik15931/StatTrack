package technik.stattrack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import technik.stattrack.Commands.StatTrackCommand;
import technik.stattrack.Events.BlockBreak;
import technik.stattrack.Events.EntityKilled;
import technik.stattrack.Events.PlayerFlying;

public final class StatTrack extends JavaPlugin {
    @Override
    public void onEnable() {
        //getCommand("stattrackitemgive").setExecutor(new StatGiveCommand(this));      DEBUG COMMAND
        getCommand("stattrack").setExecutor(new StatTrackCommand(this));
        Bukkit.getPluginManager().registerEvents(new BlockBreak(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityKilled(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFlying(this), this);
        Bukkit.getPluginManager().registerEvents(new AnvilCombine(this), this);
        saveDefaultConfig();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}