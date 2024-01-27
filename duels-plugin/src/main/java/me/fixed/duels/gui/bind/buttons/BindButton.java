package me.fixed.duels.gui.bind.buttons;

import java.util.stream.Collectors;
import lombok.Setter;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.arena.ArenaImpl;
import me.fixed.duels.gui.BaseButton;
import me.fixed.duels.gui.bind.BindGui;
import me.fixed.duels.kit.KitImpl;
import me.fixed.duels.util.StringUtil;
import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.inventory.ItemBuilder;
import org.bukkit.entity.Player;

public class BindButton extends BaseButton {

    @Setter
    private BindGui gui;
    private final KitImpl kit;
    private final ArenaImpl arena;

    public BindButton(final DuelsPlugin plugin, final KitImpl kit, final ArenaImpl arena) {
        super(plugin, ItemBuilder.of(Items.EMPTY_MAP).build());
        this.kit = kit;
        this.arena = arena;
        setDisplayName(plugin.getLang().getMessage("GUI.bind.buttons.arena.name", "arena", arena.getName()));
        update();
    }

    private void update() {
        final boolean state = arena.isBound(kit);
        setGlow(state);

        String kits = StringUtil.join(arena.getKits().stream().map(KitImpl::getName).collect(Collectors.toList()), ", ");
        kits = kits.isEmpty() ? lang.getMessage("GENERAL.none") : kits;
        setLore(lang.getMessage("GUI.bind.buttons.arena.lore-" + (state ? "bound" : "not-bound"), "kits", kits).split("\n"));
    }

    @Override
    public void onClick(final Player player) {
        arena.bind(kit);
        update();
        gui.calculatePages();
    }
}
