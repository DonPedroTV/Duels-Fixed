package me.fixed.duels.gui.options.buttons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.gui.BaseButton;
import me.fixed.duels.gui.options.OptionsGui;
import me.fixed.duels.kit.KitImpl;
import me.fixed.duels.util.inventory.ItemBuilder;
import org.bukkit.entity.Player;

public class OptionButton extends BaseButton {

    private final OptionsGui gui;
    private final KitImpl kit;
    private final OptionsGui.Option option;

    public OptionButton(final DuelsPlugin plugin, final OptionsGui gui, final KitImpl kit, final OptionsGui.Option option) {
        super(plugin, ItemBuilder.of(option.getDisplayed()).build());
        this.gui = gui;
        this.kit = kit;
        this.option = option;
        setDisplayName(plugin.getLang().getMessage("GUI.options.buttons.option.name", "name", option.name().toLowerCase()));
        update();
    }

    private void update() {
        final boolean state = option.get(kit);
        setGlow(state);

        final List<String> lore = new ArrayList<>();

        for (final String line : option.getDescription()) {
            lore.add("&f" + line.replace("%kit%", kit.getName()));
        }

        Collections.addAll(lore,
            lang.getMessage("GUI.options.buttons.option.lore", "state", state ? lang.getMessage("GENERAL.enabled") : lang.getMessage("GENERAL.disabled")).split("\n"));
        setLore(lore);
    }

    @Override
    public void onClick(final Player player) {
        option.set(kit);
        update();
        gui.update(player, this);
    }
}
