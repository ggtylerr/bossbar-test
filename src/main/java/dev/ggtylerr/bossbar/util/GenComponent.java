package dev.ggtylerr.bossbar.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class GenComponent {
    public static TextComponent bar(BossBar bar, NamespacedKey key) {
        TextComponent out = new TextComponent("[" + bar.getTitle() + "]");
        out.setColor(colorConvert(bar.getColor()));
        out.setBold(true);
        String segments = "0";
        BarStyle style = bar.getStyle();
        if (style == BarStyle.SEGMENTED_6)  segments = "6";
        if (style == BarStyle.SEGMENTED_10) segments = "10";
        if (style == BarStyle.SEGMENTED_12) segments = "12";
        if (style == BarStyle.SEGMENTED_20) segments = "20";
        String flags = "";
        if (bar.hasFlag(BarFlag.CREATE_FOG))        flags += "fog, ";
        if (bar.hasFlag(BarFlag.DARKEN_SKY))        flags += "dark, ";
        if (bar.hasFlag(BarFlag.PLAY_BOSS_MUSIC))   flags += "music, ";
        if (flags.equals("")) flags = "(none)";
        else flags = flags.substring(0, flags.length() - 2);
        out.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(
                        "Key: " + key + " (click to copy)\n" +
                        "Color: " + bar.getColor().name() + "\n" +
                        "Segments: " + segments + "\n" +
                        "Progress: " + bar.getProgress() + "\n" +
                        "Flags: " + flags
                )
        ));
        out.setClickEvent(new ClickEvent(
                ClickEvent.Action.COPY_TO_CLIPBOARD,
                key.toString()
        ));
        return out;
    }
    public static ChatColor colorConvert(BarColor color) {
        if (color == BarColor.RED)      return ChatColor.RED;
        if (color == BarColor.YELLOW)   return ChatColor.YELLOW;
        if (color == BarColor.GREEN)    return ChatColor.GREEN;
        if (color == BarColor.BLUE)     return ChatColor.BLUE;
        if (color == BarColor.PURPLE)   return ChatColor.DARK_PURPLE;
        if (color == BarColor.PINK)     return ChatColor.LIGHT_PURPLE;
                                        return ChatColor.WHITE;
    }
}
