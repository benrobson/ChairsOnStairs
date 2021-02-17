package me.benrobson.ChairsOnStairs;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChairsOnStairsMain extends JavaPlugin implements Listener {
    public static ChairsOnStairsMain plugin;
    
    @Override
    public void onEnable() {
        plugin = this;

        // Plugin Load Message
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n" + plugin.getDescription().getName() + " is now enabled.\nRunning Version: " + plugin.getDescription().getVersion() + "\nGitHub Repository: https://github.com/benrobson/ChairsOnStairs\nCreated By: " + plugin.getDescription().getAuthors() + "\n\n");

        // Plugin Event Register
        PluginManager pluginmanager = this.getServer().getPluginManager();
        pluginmanager.registerEvents(new ChairSittingEvent(this), this);

        plugin.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin Shutdown Message
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\n" + plugin.getDescription().getName() + " is now disabled.\n\n");
    }

}
