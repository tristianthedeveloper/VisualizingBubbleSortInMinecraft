package com.tristian.bubble.listeners;

import com.tristian.bubble.Sorter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SortListener implements Listener {


    @EventHandler
    public void onUse(PlayerInteractEvent e) {

        if (e.getItem() == null || e.getItem().getType() == Material.AIR)
            return;

        ItemStack used = e.getItem();
        if (used.getItemMeta() == null || used.getItemMeta().getDisplayName() == null)
            return;
        boolean start = used.getItemMeta().getDisplayName().contains("Sort");
        boolean reset = used.getItemMeta().getDisplayName().contains("Reset");

        if (start) {
            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            Sorter.getInstance().sort();
        }else if (reset) {
            Sorter.numberStandList.clear();
            Sorter.getInstance().setUpEntityMap(e.getPlayer(), Sorter.getInstance().mapStart.setDirection(Sorter.getInstance().mapStart.getDirection().multiply(-2)));
        }
    }


}
