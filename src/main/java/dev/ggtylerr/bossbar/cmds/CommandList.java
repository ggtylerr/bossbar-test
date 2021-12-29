package dev.ggtylerr.bossbar.cmds;

import dev.ggtylerr.bossbar.Main;
import dev.ggtylerr.bossbar.util.GenComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class CommandList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Iterator<KeyedBossBar> bars = Bukkit.getBossBars();
        if (!bars.hasNext()) {
            sender.sendMessage(ChatColor.RED + "There are no bars made!");
            return false;
        }
        TextComponent msg = new TextComponent("");
        msg.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        String unstyled = "";
        while (bars.hasNext()) {
            KeyedBossBar bar = bars.next();
            NamespacedKey key = bar.getKey();
            if (key.getNamespace().equals(Main.getPlugin(Main.class).getName().toLowerCase())) {
                msg.addExtra(GenComponent.bar(bar,key));
                unstyled += bar.getTitle() + " (" + key + "), ";
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.spigot().sendMessage(msg);
        }
        else sender.sendMessage(ChatColor.GREEN + unstyled);
        return true;
    }
}
