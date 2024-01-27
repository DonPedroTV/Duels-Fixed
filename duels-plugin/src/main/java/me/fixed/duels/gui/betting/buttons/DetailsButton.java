package me.fixed.duels.gui.betting.buttons;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.gui.BaseButton;
import me.fixed.duels.setting.Settings;
import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.inventory.ItemBuilder;
import org.bukkit.entity.Player;

public class DetailsButton extends BaseButton {

    private final Settings settings;

    public DetailsButton(final DuelsPlugin plugin, final Settings settings) {
        super(plugin, ItemBuilder
            .of(Items.SIGN)
            .name(plugin.getLang().getMessage("GUI.item-betting.buttons.details.name"))
            .build()
        );
        this.settings = settings;
    }

    @Override
    public void update(final Player player) {
        final String lore = lang.getMessage("GUI.item-betting.buttons.details.lore",
            "kit", settings.getKit() != null ? settings.getKit().getName() : lang.getMessage("GENERAL.not-selected"),
            "arena", settings.getArena() != null ? settings.getArena().getName() : lang.getMessage("GENERAL.random"),
            "bet_amount", settings.getBet()
        );
        setLore(lore.split("\n"));
    }
}
