package me.fixed.duels.hook.hooks;

import me.fixed.duels.util.hook.PluginHook;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.arena.ArenaManagerImpl;
import me.fixed.duels.config.Config;
import net.sacredlabyrinth.phaed.simpleclans.events.AddKillEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SimpleClansHook extends PluginHook<DuelsPlugin> {

    public static final String NAME = "SimpleClans";

    private final Config config;
    private final ArenaManagerImpl arenaManager;

    public SimpleClansHook(final DuelsPlugin plugin) {
        super(plugin, NAME);
        this.config = plugin.getConfiguration();
        this.arenaManager = plugin.getArenaManager();

        try {
            Class.forName("net.sacredlabyrinth.phaed.simpleclans.events.AddKillEvent");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("This version of " + getName() + " is not supported. Please try upgrading to the latest version.");
        }

        Bukkit.getPluginManager().registerEvents(new SimpleClansListener(), plugin);
    }

    public class SimpleClansListener implements Listener {

        @EventHandler(ignoreCancelled = true)
        public void on(final AddKillEvent event) {
            if (!config.isPreventKDRChange()) {
                return;
            }

            final Player player = event.getVictim().toPlayer();

            if (player == null || !arenaManager.isInMatch(player)) {
                return;
            }

            event.setCancelled(true);
        }

    }
}
