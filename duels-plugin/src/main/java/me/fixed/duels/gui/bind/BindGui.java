package me.fixed.duels.gui.bind;

import java.util.stream.Collectors;

import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.gui.MultiPageGui;
import me.fixed.duels.util.inventory.ItemBuilder;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.config.Config;
import me.fixed.duels.config.Lang;
import me.fixed.duels.gui.bind.buttons.BindButton;
import me.fixed.duels.kit.KitImpl;
import org.bukkit.Material;

public class BindGui extends MultiPageGui<DuelsPlugin> {

    public BindGui(final DuelsPlugin plugin, final KitImpl kit) {
        super(plugin, plugin.getLang().getMessage("GUI.bind.title", "kit", kit.getName()), plugin.getConfiguration().getArenaSelectorRows(),
            plugin.getArenaManager().getArenasImpl().stream().map(arena -> new BindButton(plugin, kit, arena)).collect(Collectors.toList()));

        final Config config = plugin.getConfiguration();
        final Lang lang = plugin.getLang();
        setSpaceFiller(Items.from(config.getArenaSelectorFillerType(), config.getArenaSelectorFillerData()));
        setPrevButton(ItemBuilder.of(Material.PAPER).name(lang.getMessage("GUI.arena-selector.buttons.previous-page.name")).build());
        setNextButton(ItemBuilder.of(Material.PAPER).name(lang.getMessage("GUI.arena-selector.buttons.next-page.name")).build());
        setEmptyIndicator(ItemBuilder.of(Material.PAPER).name(lang.getMessage("GUI.arena-selector.buttons.empty.name")).build());
        // Change design to remove this loop in the future
        getButtons().forEach(button -> ((BindButton) button).setGui(this));
        calculatePages();
    }
}
