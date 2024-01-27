package me.fixed.duels.betting;

import me.fixed.duels.gui.betting.BettingGui;
import me.fixed.duels.hook.hooks.VaultHook;
import me.fixed.duels.setting.Settings;
import me.fixed.duels.util.gui.GuiListener;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.util.Loadable;
import me.fixed.duels.util.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BettingManager implements Loadable, Listener {

    private final DuelsPlugin plugin;
    private final GuiListener<DuelsPlugin> guiListener;

    public BettingManager(final DuelsPlugin plugin) {
        this.plugin = plugin;
        this.guiListener = plugin.getGuiListener();
    }

    @Override
    public void handleLoad() {
        final VaultHook vaultHook = plugin.getHookManager().getHook(VaultHook.class);

        if (vaultHook == null) {
            Log.info(this, "Vault was not found! Money betting feature will be automatically disabled.");
        } else if (vaultHook.getEconomy() == null) {
            Log.info(this, "Economy plugin supporting Vault was not found! Money betting feature will be automatically disabled.");
        }
    }

    @Override
    public void handleUnload() {}

    public void open(final Settings settings, final Player first, final Player second) {
        final BettingGui gui = new BettingGui(plugin, settings, first, second);
        guiListener.addGui(first, gui).open(first);
        guiListener.addGui(second, gui).open(second);
    }
}
