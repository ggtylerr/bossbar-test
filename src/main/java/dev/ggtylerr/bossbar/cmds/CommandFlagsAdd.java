package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import dev.ggtylerr.bossbar.util.TabCompletion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandFlagsAdd implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Incorrect # of arguments provided!");
            return false;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), args[0]);
        BossBar bar = Bukkit.getBossBar(key);
        if (bar == null) {
            sender.sendMessage(ChatColor.RED + "That bar doesn't exist!");
            return false;
        }
        List<String> success = new ArrayList<>();
        List<String> fails = new ArrayList<>();
        for (String arg : Arrays.copyOfRange(args, 1, args.length)) {
            switch (arg) {
                case "all":
                    bar.addFlag(BarFlag.CREATE_FOG);
                    bar.addFlag(BarFlag.DARKEN_SKY);
                    bar.addFlag(BarFlag.PLAY_BOSS_MUSIC);
                    if (!success.contains("fog")) success.add("fog");
                    if (!success.contains("dark")) success.add("dark");
                    if (!success.contains("music")) success.add("music");
                    break;
                case "fog":
                    bar.addFlag(BarFlag.CREATE_FOG);
                    if (!success.contains(arg)) success.add(arg);
                    break;
                case "dark":
                    bar.addFlag(BarFlag.DARKEN_SKY);
                    if (!success.contains(arg)) success.add(arg);
                    break;
                case "music":
                    bar.addFlag(BarFlag.PLAY_BOSS_MUSIC);
                    if (!success.contains(arg)) success.add(arg);
                    break;
                default:
                    fails.add(arg);
                    break;
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                TextComponent msg;
                if (success.size() == 0) {
                    msg = new TextComponent("The flag '" + fails.get(0) + "' does not exist!");
                    msg.setColor(net.md_5.bungee.api.ChatColor.RED);
                    player.spigot().sendMessage(msg);
                    return false;
                } else {
                    msg = new TextComponent("Successfully added the flag '" + success.get(0) + "' to the bar ");
                    msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    msg.addExtra(GenComponent.bar(bar, key));
                    player.spigot().sendMessage(msg);
                }
            } else {
                TextComponent msg = new TextComponent("Flags successfully added to ");
                msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                msg.addExtra(GenComponent.bar(bar, key));
                msg.addExtra(": ");
                if (success.size() == 0) {
                    TextComponent none = new TextComponent("(none)");
                    none.setColor(net.md_5.bungee.api.ChatColor.RED);
                    msg.addExtra(none);
                }
                else {
                    for (String textComponent : success) {
                        msg.addExtra(textComponent);
                        msg.addExtra(" ");
                    }
                }
                TextComponent failMsg = new TextComponent("\nFlags not added (they probably don't exist): ");
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
            if (args.length == 2) {
                if (success.size() == 0) {
                    sender.sendMessage(ChatColor.RED + "The flag '" + fails.get(0) + "' does not exist!");
                    return false;
                }
                else {
                    sender.sendMessage(ChatColor.GREEN + "Successfully added the flag '" + success.get(0) + "' to the bar " + bar.getTitle());
                }
            } else {
                String successStr = (success.size() == 0) ? ChatColor.RED + "(none)" : success.toString();
                String failStr = (fails.size() == 0) ? ChatColor.GREEN + "(none)" : fails.toString();
                sender.sendMessage(
                        ChatColor.GREEN + "Flags successfully added to " + bar.getTitle() + ": " +
                                successStr + "\n" +
                                ChatColor.RED + "Flags not added (they probably don't exist): " +
                                failStr
                );
            }
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        if (args.length > 1) return Arrays.asList("fog", "dark", "music");
        return null;
    }
}
