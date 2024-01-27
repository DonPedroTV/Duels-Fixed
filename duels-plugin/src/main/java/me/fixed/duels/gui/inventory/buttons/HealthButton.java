package me.fixed.duels.gui.inventory.buttons;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.gui.BaseButton;
import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.inventory.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HealthButton extends BaseButton {

    public HealthButton(final DuelsPlugin plugin, final Player player, final boolean dead) {
        super(plugin, ItemBuilder
            .of(dead ? Items.SKELETON_HEAD : Material.GOLDEN_APPLE)
            .name(plugin.getLang().getMessage("GUI.inventory-view.buttons.health.name", "health", dead ? 0 : Math.ceil(player.getHealth()) * 0.5))
            .build());
    }
}
