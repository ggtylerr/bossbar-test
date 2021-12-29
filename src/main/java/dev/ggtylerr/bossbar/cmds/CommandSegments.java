package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import dev.ggtylerr.bossbar.util.TabCompletion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandSegments implements TabExecutor {
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
            String segments = "0";
            BarStyle style = bar.getStyle();
            if (style == BarStyle.SEGMENTED_6)  segments = "6";
            if (style == BarStyle.SEGMENTED_10) segments = "10";
            if (style == BarStyle.SEGMENTED_12) segments = "12";
            if (style == BarStyle.SEGMENTED_20) segments = "20";
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("The bar ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(" has " + segments + " segments");
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "The bar '" + bar.getTitle() + "' has " + segments + " segments");
        }
        else {
            List<String> a = Arrays.asList("0","6","10","12","20");
            if (!a.contains(args[1])) {
                sender.sendMessage(ChatColor.RED + "That's not a valid amount of segments!");
                return false;
            }
            BarStyle segments = BarStyle.SOLID;
            if (args[1].equals("6"))    segments = BarStyle.SEGMENTED_6;
            if (args[1].equals("10"))   segments = BarStyle.SEGMENTED_10;
            if (args[1].equals("12"))   segments = BarStyle.SEGMENTED_12;
            if (args[1].equals("20"))   segments = BarStyle.SEGMENTED_20;
            bar.setStyle(segments);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("Set the bar ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(" segments to " + args[1]);
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "Set that bar's segments to " + args[1]);
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        if (args.length == 2) return Arrays.asList("0","6","10","12","20");
        return null;
    }
}
