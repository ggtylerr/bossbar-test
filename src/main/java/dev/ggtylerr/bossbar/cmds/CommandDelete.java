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

import java.util.ArrayList;
import java.util.List;

public class CommandDelete implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "No arguments provided!");
            return false;
        }
        List<TextComponent> success = new ArrayList<>();
        List<String> successTitles = new ArrayList<>();
        List<String> fails = new ArrayList<>();
        for (String arg : args) {
            NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), arg);
            BossBar bar = Bukkit.getBossBar(key);
            if (bar == null) fails.add(arg);
            else {
                success.add(GenComponent.bar(bar, key));
                successTitles.add(bar.getTitle());
                bar.setVisible(false);
                Bukkit.removeBossBar(key);
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                TextComponent msg;
                if (success.size() == 0) {
                    msg = new TextComponent("The bar '" + fails.get(0) + "' does not exist!");
                    msg.setColor(net.md_5.bungee.api.ChatColor.RED);
                    player.spigot().sendMessage(msg);
                    return false;
                } else {
                    msg = new TextComponent("Successfully deleted the bar ");
                    msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    msg.addExtra(success.get(0));
                    player.spigot().sendMessage(msg);
                }
            }
            else {
                TextComponent msg = new TextComponent("Bars successfully deleted: ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                if (success.size() == 0) {
                    TextComponent none = new TextComponent("(none)");
                    none.setColor(net.md_5.bungee.api.ChatColor.RED);
                    msg.addExtra(none);
                }
                else {
                    for (TextComponent textComponent : success) {
                        msg.addExtra(textComponent);
                        msg.addExtra(" ");
                    }
                }
                TextComponent failMsg = new TextComponent("\nBars not deleted (they probably don't exist): ");
                failMsg.setColor(net.md_5.bungee.api.ChatColor.RED);
                msg.addExtra(failMsg);
                if (fails.size() == 0) {
                    TextComponent none = new TextComponent("(none)");
                    none.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    msg.addExtra(none);
                }
                else {
                    for (String s : fails) {
                        msg.addExtra(s + " ");
                    }
                }
                player.spigot().sendMessage(msg);
            }
        }
        else {
            if (args.length == 1) {
                if (success.size() == 0) {
                    sender.sendMessage(ChatColor.RED + "The bar '" + fails.get(0) + "' does not exist!");
                    return false;
                }
                else {
                    sender.sendMessage(ChatColor.GREEN + "Successfully deleted the bar " + successTitles.get(0));
                }
            } else {
                String successStr = (successTitles.size() == 0) ? ChatColor.RED + "(none)" : successTitles.toString();
                String failStr = (fails.size() == 0) ? ChatColor.GREEN + "(none)" : fails.toString();
                sender.sendMessage(
                        ChatColor.GREEN + "Bars successfully deleted: " +
                        successStr + "\n" +
                        ChatColor.RED + "Bars not deleted (they probably don't exist): " +
                        failStr
                );
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 0) return TabCompletion.bar();
        return null;
    }
}
