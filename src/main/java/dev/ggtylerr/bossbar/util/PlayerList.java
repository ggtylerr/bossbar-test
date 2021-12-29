package dev.ggtylerr.bossbar.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    public static List<String> toStringList(List<Player> l) {
        List<String> out = new ArrayList<>();
        for (Player p : l) {
            out.add(p.getDisplayName());
        }
        return out;
    }
}
