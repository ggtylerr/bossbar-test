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

public class CommandText implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "No arguments provided!");
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
                TextComponent msg = new TextComponent("That bar's text is ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "That bar's text is " + bar.getTitle());
        }
        else {
            String title = String.join( "", Arrays.copyOfRange(args, 1, args.length) );
            bar.setTitle(title);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TextComponent msg = new TextComponent("Set that bar's text to ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                player.spigot().sendMessage(msg);
            }
            else sender.sendMessage(ChatColor.GREEN + "Set that bar's text to " + title);
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        return null;
    }
}
