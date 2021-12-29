package dev.ggtylerr.bossbar.util;

import dev.ggtylerr.bossbar.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.KeyedBossBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabCompletion {
    public static List<String> bar() {
        List<String> out = new ArrayList<>();
        Iterator<KeyedBossBar> bars = Bukkit.getBossBars();
        if (!bars.hasNext()) return null;
        while (bars.hasNext()) {
            KeyedBossBar bar = bars.next();
            if (bar.getKey().getNamespace().equals(Main.getPlugin(Main.class).getName().toLowerCase())) {
                out.add(bar.getKey().getKey());
            }
        }
        return out;
    }
}
