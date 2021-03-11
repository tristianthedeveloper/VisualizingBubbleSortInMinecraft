package com.tristian.bubble;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.UUID;

public class NumberStand {

    public int value;
    public Location position;

    public Entity ent;
    public ArmorStand castEntity;
    public UUID entId;

    public NumberStand(int number, Location l) {
        value = number;
        position = l;
        genArmorStand();
    }

    private void genArmorStand() {
        ItemStack skull = NumberEnum.getByNumber(this.value).getSkull();
        ArmorStand ent = (ArmorStand) this.position.getWorld().spawnEntity(position, EntityType.ARMOR_STAND);
        ent.setMetadata("value", new FixedMetadataValue(VisualizingBubbleSort.getInstance(), this.value));
        ent.setGravity(false);
        ent.setHelmet(skull);
        ent.setVelocity(new Vector(0, 0, 0));
        ent.setVisible(false);
        this.entId = ent.getUniqueId();
        this.ent = ent;
        this.castEntity = ent;
        System.out.println("spawned armor stand at: " + this.position.toString());
    }


    @Override
    public String toString() {
        return "NumberStand@" + this.hashCode() + "{position=" + this.position.toString() + ",value=" + this.value;
    }

    public void swapPositions(NumberStand next) {
        Location temporary = this.position;
        this.position = next.position;
        this.castEntity.teleport(next.position);
        next.position = temporary;
        next.castEntity.teleport(temporary);
    }
}
