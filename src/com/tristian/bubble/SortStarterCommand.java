package com.tristian.bubble;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SortStarterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Sorter.getInstance().sort();
//        System.out.println("sorting");
        return true;
    }
}
