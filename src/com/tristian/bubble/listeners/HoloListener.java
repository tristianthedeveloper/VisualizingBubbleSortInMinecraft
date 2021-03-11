package com.tristian.bubble.listeners;

import com.tristian.bubble.SkullUtil;
import com.tristian.bubble.Sorter;
import com.tristian.bubble.VisualizingBubbleSort;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class HoloListener implements Listener {




    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof ArmorStand))
            return;
        ArmorStand stand = (ArmorStand) e.getEntity();
        Player damager = (Player) e.getDamager();

        Sorter.buttons.forEach(Entity::remove);
        if (stand.hasMetadata("start")) {
            Sorter.getInstance().sort();
            ArmorStand resetStandText = (ArmorStand) damager.getWorld().spawnEntity(Sorter.getInstance().mapStart.add(1, 0, 0), EntityType.ARMOR_STAND);
            ArmorStand resetStandButton = (ArmorStand) damager.getWorld().spawnEntity(Sorter.getInstance().mapStart.add(1, 0, 0), EntityType.ARMOR_STAND);

            resetStandText.setVisible(false);
            resetStandText.setCustomNameVisible(true);
            resetStandText.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c&l(!) &cGenerate New Array"));
            resetStandText.setMetadata("reset", new FixedMetadataValue(VisualizingBubbleSort.getInstance(), true));
            resetStandText.setGravity(false);
            resetStandText.setVelocity(new Vector(0, 0, 0));

            resetStandButton.setMetadata("reset", new FixedMetadataValue(VisualizingBubbleSort.getInstance(), true));
            resetStandButton.setVisible(false);
            resetStandButton.setGravity(false);
            resetStandButton.setVelocity(new Vector(0, 0, 0));
            resetStandButton.setHelmet(SkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1Mjg2ZTNlNmZhMDBlNGE2MGJiODk2NzViOWFhNzVkNmM5Y2RkMWVjODQwZDFiY2MyOTZiNzFjOTJmOWU0MyJ9fX0="));

            Sorter.buttons.add(resetStandButton);
            Sorter.buttons.add(resetStandText);

        } else if (stand.hasMetadata("reset")) {
            Sorter.numberStandList.clear();
            Sorter.getInstance().setUpEntityMap(Sorter.getInstance().mapStart.subtract(2, 0, 0).setDirection(damager.getLocation().getDirection()));
        }
    }


}
