package com.tristian.bubble;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sorter {

    private static Sorter instance;

    public static List<ArmorStand> buttons = Lists.newArrayList();

    public static List<NumberStand> numberStandList
            = Lists.newLinkedList();

    public Location mapStart = null;


    public static Sorter getInstance() {

        if (instance == null)
            instance = new Sorter();
        return instance;
    }

    public void setUpEntityMap(Player player, Location starting) {

        Vector direction = starting.getDirection();

        Location twoBlocksInFront = starting.clone().add(direction.multiply(2));

        List<Integer> values = new LinkedList<>();
        for (int i = 1; i <= 15; i++) {
            values.add(i);
        }
        mapStart = twoBlocksInFront;
        Collections.shuffle(values, new Random());
        int k = 1;
        for (int i : values) { // 14 t// imes
            twoBlocksInFront.setDirection(starting.getDirection().multiply(-1));
            numberStandList.add(new NumberStand(i, twoBlocksInFront.clone().add(-k, 0, 0)));
            k++;
        }

        ItemStack start = SkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVmODkyNGYzMTZhMDBhOWI4MDYxMjQ5YzA4NTEzYjBjYzM3ZGQ2ZmEzMTBkMDVlMzhmM2Q5YzZiODJjZjBkYiJ9fX0=");
        ItemMeta meta = start.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &aSort!"));
        start.setItemMeta(meta);


        ItemStack reset = SkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1Mjg2ZTNlNmZhMDBlNGE2MGJiODk2NzViOWFhNzVkNmM5Y2RkMWVjODQwZDFiY2MyOTZiNzFjOTJmOWU0MyJ9fX0=");
        ItemMeta resetMeta = reset.getItemMeta();
        resetMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l(!) &cReset and generate new array!"));

        reset.setItemMeta(resetMeta);


        player.getInventory().setItem(0, start);
        player.getInventory().setItem(8, reset);



    }

    public void sort() {


        // Numberstand: Array Index

        // maintain order in the copy list
        for (int i = 0; i < numberStandList.size() - 1; i++) {
            for (int k = 0; k < numberStandList.size() - i - 1; k++) {

                NumberStand current;
                NumberStand next;
                if ((current = this.numberStandList.get(k)).value > (next = this.numberStandList.get(k + 1)).value) {
                    System.out.println("Swapping armorstand: " + current + ", and " + next);


                    int finalK = k;


                    NumberStand temp = current;

                    numberStandList.set(finalK, next);
                    numberStandList.set(finalK + 1, temp);


                    numberStandList.set(finalK, next);
                    current.swapPositions(next);
                    // use bubble to sort the armor stands

                }
            }
        }

        System.out.println("List after sorting: " + this.numberStandList);
    }

}
