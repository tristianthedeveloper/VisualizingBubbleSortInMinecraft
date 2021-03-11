package com.tristian.bubble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BubbleSortCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        VisualizingBubbleSort.getInstance().run((Player)commandSender);

        return true;

    }
}
