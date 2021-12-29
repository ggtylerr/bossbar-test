package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import dev.ggtylerr.bossbar.util.TabCompletion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandFlags implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Incorrect # of arguments provided!");
            return false;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), args[0]);
        BossBar bar = Bukkit.getBossBar(key);
        if (bar == null) {
            sender.sendMessage(ChatColor.RED + "That bar doesn't exist!");
            return false;
        }
        List<String> flags = new ArrayList<>();
        if (bar.hasFlag(BarFlag.CREATE_FOG))        flags.add("fog");
        if (bar.hasFlag(BarFlag.DARKEN_SKY))        flags.add("dark");
        if (bar.hasFlag(BarFlag.PLAY_BOSS_MUSIC))   flags.add("music");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            TextComponent msg = new TextComponent("The bar ");
            msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            msg.addExtra(GenComponent.bar(bar, key));
            if (flags.size() == 0) {
                msg.addExtra(" has no flags");
            }
            else {
                msg.addExtra(" has the flags " + flags);
            }
            player.spigot().sendMessage(msg);
        }
        else {
            if (flags.size() == 0) {
                sender.sendMessage(ChatColor.GREEN + "The bar '" + bar.getTitle() + "' has no flags");
            }
            else {
                sender.sendMessage(ChatColor.GREEN + "The bar '" + bar.getTitle() + "' has the flags " + flags);
            }
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        return null;
    }
}
