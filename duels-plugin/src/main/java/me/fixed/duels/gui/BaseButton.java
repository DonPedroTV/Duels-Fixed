package me.fixed.duels.gui;

import me.fixed.duels.arena.ArenaManagerImpl;
import me.fixed.duels.config.Config;
import me.fixed.duels.config.Lang;
import me.fixed.duels.kit.KitManagerImpl;
import me.fixed.duels.queue.QueueManager;
import me.fixed.duels.queue.sign.QueueSignManagerImpl;
import me.fixed.duels.request.RequestManager;
import me.fixed.duels.setting.SettingsManager;
import me.fixed.duels.spectate.SpectateManagerImpl;
import me.fixed.duels.util.gui.Button;
import me.fixed.duels.DuelsPlugin;
import org.bukkit.inventory.ItemStack;

public abstract class BaseButton extends Button<DuelsPlugin> {

    protected final Config config;
    protected final Lang lang;
    protected final KitManagerImpl kitManager;
    protected final ArenaManagerImpl arenaManager;
    protected final SettingsManager settingManager;
    protected final QueueManager queueManager;
    protected final QueueSignManagerImpl queueSignManager;
    protected final SpectateManagerImpl spectateManager;
    protected final RequestManager requestManager;

    protected BaseButton(final DuelsPlugin plugin, final ItemStack displayed) {
        super(plugin, displayed);
        this.config = plugin.getConfiguration();
        this.lang = plugin.getLang();
        this.kitManager = plugin.getKitManager();
        this.arenaManager = plugin.getArenaManager();
        this.settingManager = plugin.getSettingManager();
        this.queueManager = plugin.getQueueManager();
        this.queueSignManager = plugin.getQueueSignManager();
        this.spectateManager = plugin.getSpectateManager();
        this.requestManager = plugin.getRequestManager();
    }
}
