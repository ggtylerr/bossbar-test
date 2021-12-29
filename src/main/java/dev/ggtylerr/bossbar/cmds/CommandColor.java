package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import dev.ggtylerr.bossbar.util.TabCompletion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandColor implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "No arguments provided!");
            return false;
        }
        if (args.length > 2) {
            sender.sendMessage(ChatColor.RED + "Too many arguments provided!");
            return false;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), args[0]);
        BossBar bar = Bukkit.getBossBar(key);
        if (bar == null) {
            sender.sendMessage(ChatColor.RED + "That bar doesn't exist!");
            return false;
        }
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("The bar ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(" has the color ");
                TextComponent colorMsg = new TextComponent(bar.getColor().toString());
                colorMsg.setColor(GenComponent.colorConvert(bar.getColor()));
                msg.addExtra(colorMsg);
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "The bar '" + bar.getTitle() + "' has the color "
                    + GenComponent.colorConvert(bar.getColor()) + bar.getColor());
        }
        else {
            String color = args[1].toLowerCase();
            if (color.equals("red"))    bar.setColor(BarColor.RED);
            if (color.equals("yellow")) bar.setColor(BarColor.YELLOW);
            if (color.equals("green"))  bar.setColor(BarColor.GREEN);
            if (color.equals("blue"))   bar.setColor(BarColor.BLUE);
            if (color.equals("purple")) bar.setColor(BarColor.PURPLE);
            if (color.equals("pink"))   bar.setColor(BarColor.PINK);
            if (color.equals("white"))  bar.setColor(BarColor.WHITE);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("Set the bar ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(" color to ");
                TextComponent colorMsg = new TextComponent(bar.getColor().toString());
                colorMsg.setColor(GenComponent.colorConvert(bar.getColor()));
                msg.addExtra(colorMsg);
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "Set that bar's color to " +
                    GenComponent.colorConvert(bar.getColor()) + bar.getColor());
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        if (args.length == 2) return Arrays.asList("red", "yellow", "green", "blue", "purple", "pink", "white");
        return null;
    }
}
