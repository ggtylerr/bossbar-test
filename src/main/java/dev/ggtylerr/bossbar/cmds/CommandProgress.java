package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import dev.ggtylerr.bossbar.util.TabCompletion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandProgress implements TabExecutor {
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
                msg.addExtra(" has the progress " + bar.getProgress());
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "The bar '" + bar.getTitle() + "' has the progress " + bar.getProgress());
        }
        else {
            double progress;
            try {
                progress = Double.parseDouble(args[1]);
            } catch(NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "2nd argument is not a decimal! (i.e. 1.0)");
                return false;
            }
            bar.setProgress(progress);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("Set the bar ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(" progress to " + progress);
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "Set that bar's progress to " + progress);
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        if (args.length == 2) return Arrays.asList("0.0","0.1","0.2","0.25","0.3","0.4","0.5","0.6","0.7","0.75","0.8","0.9","1.0");
        return null;
    }
}
