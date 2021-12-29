package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandCreate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments provided!");
            return false;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), args[0]);
        if (Bukkit.getBossBar(key) != null) {
            sender.sendMessage(ChatColor.RED + "That bar has already been made!");
            return false;
        }
        String title = String.join( "", Arrays.copyOfRange(args, 1, args.length) );
        BossBar bb = Bukkit.createBossBar(key, title, BarColor.RED, BarStyle.SOLID);
        bb.setProgress(1.0);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            bb.addPlayer(player);
            TextComponent msg = new TextComponent("Bar ");
            msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            msg.addExtra(GenComponent.bar(bb,key));
            msg.addExtra(" successfully created!");
            player.spigot().sendMessage(msg);
        }
        else sender.sendMessage(ChatColor.GREEN + "Bar '" + title + "' successfully created under the key " + key);
        return true;
    }
}
