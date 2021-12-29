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

import java.util.List;

public class CommandCopy implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Incorrect # of arguments provided!");
            return false;
        }
        NamespacedKey oldKey = new NamespacedKey(Main.getPlugin(Main.class), args[0]);
        BossBar oldBar = Bukkit.getBossBar(oldKey);
        if (oldBar == null) {
            sender.sendMessage(ChatColor.RED + "The bar you're trying to copy doesn't exist!");
            return false;
        }
        NamespacedKey newKey = new NamespacedKey(Main.getPlugin(Main.class), args[1]);
        if (Bukkit.getBossBar(newKey) != null) {
            sender.sendMessage(ChatColor.RED + "The bar you're trying to copy to already exists!");
            return false;
        }
        BossBar newBar = Bukkit.createBossBar(newKey, oldBar.getTitle(), oldBar.getColor(), oldBar.getStyle());
        newBar.setProgress(oldBar.getProgress());
        if (oldBar.hasFlag(BarFlag.CREATE_FOG))         newBar.addFlag(BarFlag.CREATE_FOG);
        if (oldBar.hasFlag(BarFlag.DARKEN_SKY))         newBar.addFlag(BarFlag.DARKEN_SKY);
        if (oldBar.hasFlag(BarFlag.PLAY_BOSS_MUSIC))    newBar.addFlag(BarFlag.PLAY_BOSS_MUSIC);
        List<Player> players = oldBar.getPlayers();
        for (Player p : players) {
            newBar.addPlayer(p);
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            TextComponent msg = new TextComponent("Bar ");
            msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            msg.addExtra(GenComponent.bar(oldBar, oldKey));
            msg.addExtra(" successfully copied to ");
            msg.addExtra(GenComponent.bar(newBar, newKey));
            player.spigot().sendMessage(msg);
        }
        else sender.sendMessage(ChatColor.GREEN + "Bar '" + oldBar.getTitle() + "' successfully copied to '" + newBar.getTitle() + "'");
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return TabCompletion.bar();
        return null;
    }
}