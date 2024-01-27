package me.realized.duels.listeners;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.arena.ArenaManagerImpl;
import me.realized.duels.config.Config;
import me.realized.duels.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

/**
 * Displays a message to shooter containing hit entity's health if enabled.
 */
public class ProjectileHitListener implements Listener {

    private final Config config;
    private final Lang lang;
    private final ArenaManagerImpl arenaManager;

    public ProjectileHitListener(final DuelsPlugin plugin) {
        this.config = plugin.getConfiguration();
        this.lang = plugin.getLang();
        this.arenaManager = plugin.getArenaManager();

        if (plugin.getConfiguration().isProjectileHitMessageEnabled()) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void on(final EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        final Entity damager = event.getDamager();

        if (!(damager instanceof Projectile) || !config.getProjectileHitMessageTypes().contains(damager.getType().name())) {
            return;
        }

        final ProjectileSource source = ((Projectile) damager).getShooter();

        if (!(source instanceof Player)) {
            return;
        }

        final Player player = (Player) source;

        if (!arenaManager.isInMatch(player)) {
            return;
        }

        final LivingEntity entity = (LivingEntity) event.getEntity();
        final double health = Math.max(Math.ceil(entity.getHealth() - event.getFinalDamage()) * 0.5, 0);
        lang.sendMessage(player, "DUEL.projectile-hit-message", "name", entity.getName(), "health", health);
    }
}
