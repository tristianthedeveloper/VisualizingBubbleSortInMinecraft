package com.tristian.bubble;

import com.tristian.bubble.listeners.HoloListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VisualizingBubbleSort extends JavaPlugin {

    private static VisualizingBubbleSort instance;

    private static Sorter s;

    @Override
    public void onEnable() {

        getCommand("bubblesort").setExecutor(new BubbleSortCommand());
        getCommand("sort").setExecutor(new SortStarterCommand());
        Bukkit.getPluginManager().registerEvents(new HoloListener(), this);
        instance = this;

    }


    public void run(Player p) {
        s = Sorter.getInstance();
        s.setUpEntityMap(p.getLocation());

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &aMap set up, /sort to sort"));
    }



    public static VisualizingBubbleSort getInstance() {
        return instance;
    }
}
