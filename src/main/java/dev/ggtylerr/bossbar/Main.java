package dev.ggtylerr.bossbar;

import dev.ggtylerr.bossbar.cmds.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // I'm not putting a Hello World in here, lmao.
        this.getCommand("bbcreate").setExecutor(new CommandCreate());
        this.getCommand("bbdelete").setExecutor(new CommandDelete());
        this.getCommand("bbdelete").setTabCompleter(new CommandDelete());
        this.getCommand("bbcopy").setExecutor(new CommandCopy());
        this.getCommand("bbcopy").setTabCompleter(new CommandCopy());
        this.getCommand("bbcut").setExecutor(new CommandCut());
        this.getCommand("bbcut").setTabCompleter(new CommandCut());
        this.getCommand("bblist").setExecutor(new CommandList());

        this.getCommand("bbtext").setExecutor(new CommandText());
        this.getCommand("bbtext").setTabCompleter(new CommandText());
        this.getCommand("bbcolor").setExecutor(new CommandColor());
        this.getCommand("bbcolor").setTabCompleter(new CommandColor());
        this.getCommand("bbprogress").setExecutor(new CommandProgress());
        this.getCommand("bbprogress").setTabCompleter(new CommandProgress());
        this.getCommand("bbsegments").setExecutor(new CommandSegments());
        this.getCommand("bbsegments").setTabCompleter(new CommandSegments());

        this.getCommand("bbflags").setExecutor(new CommandFlags());
        this.getCommand("bbflags").setTabCompleter(new CommandFlags());
        this.getCommand("bbaflags").setExecutor(new CommandFlagsAdd());
        this.getCommand("bbaflags").setTabCompleter(new CommandFlagsAdd());
        this.getCommand("bbrflags").setExecutor(new CommandFlagsRemove());
        this.getCommand("bbrflags").setTabCompleter(new CommandFlagsRemove());

        this.getCommand("bbplayers").setExecutor(new CommandPlayers());
        this.getCommand("bbplayers").setTabCompleter(new CommandPlayers());
        this.getCommand("bbaplayers").setExecutor(new CommandPlayersAdd());
        this.getCommand("bbaplayers").setTabCompleter(new CommandPlayersAdd());
        this.getCommand("bbrplayers").setExecutor(new CommandPlayersRemove());
        this.getCommand("bbrplayers").setTabCompleter(new CommandPlayersRemove());

        this.getCommand("bbshow").setExecutor(new CommandShow());
        this.getCommand("bbshow").setTabCompleter(new CommandShow());
        this.getCommand("bbhide").setExecutor(new CommandHide());
        this.getCommand("bbhide").setTabCompleter(new CommandHide());
        this.getCommand("bbtoggle").setExecutor(new CommandToggle());
        this.getCommand("bbtoggle").setTabCompleter(new CommandToggle());
    }

    @Override
    public void onDisable() {
        // Not putting a Goodbye World here either.
    }
}
