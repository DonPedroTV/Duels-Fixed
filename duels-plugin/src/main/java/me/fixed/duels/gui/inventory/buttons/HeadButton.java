package me.fixed.duels.gui.inventory.buttons;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.gui.BaseButton;
import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.inventory.ItemBuilder;
import org.bukkit.entity.Player;

public class HeadButton extends BaseButton {

    public HeadButton(final DuelsPlugin plugin, final Player owner) {
        super(plugin, ItemBuilder
            .of(Items.HEAD.clone())
            .name(plugin.getLang().getMessage("GUI.inventory-view.buttons.head.name", "name", owner.getName()))
            .build()
        );
        setOwner(owner);
    }
}
