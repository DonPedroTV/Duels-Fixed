package me.fixed.duels.gui.options;

import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Getter;
import me.fixed.duels.util.compat.Items;
import me.fixed.duels.util.gui.SinglePageGui;
import me.fixed.duels.util.inventory.Slots;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.gui.options.buttons.OptionButton;
import me.fixed.duels.kit.KitImpl;
import me.fixed.duels.kit.KitImpl.Characteristic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OptionsGui extends SinglePageGui<DuelsPlugin> {

    private final DuelsPlugin plugin;
    private final Player owner;

    public OptionsGui(final DuelsPlugin plugin, final Player player, final KitImpl kit) {
        super(plugin, plugin.getLang().getMessage("GUI.options.title", "kit", kit.getName()), 2);
        this.plugin = plugin;
        this.owner = player;

        int i = 0;

        for (final Option option : Option.values()) {
            set(i++, new OptionButton(plugin, this, kit, option));
        }

        final ItemStack spacing = Items.WHITE_PANE.clone();
        Slots.run(9, 18, slot -> inventory.setItem(slot, spacing));
    }

    @Override
    public void on(final Player player, final Inventory inventory, final InventoryCloseEvent event) {
        plugin.getGuiListener().removeGui(owner, this);
    }

    public enum Option {

        USEPERMISSION(Material.BARRIER, KitImpl::isUsePermission, kit -> kit.setUsePermission(!kit.isUsePermission()), "When enabled, players must", "have the permission duels.kits.%kit%", "to select this kit for duel."),
        ARENASPECIFIC(Items.EMPTY_MAP, KitImpl::isArenaSpecific, kit -> kit.setArenaSpecific(!kit.isArenaSpecific()), "When enabled, kit %kit%", "can only be used in", "arenas it is bound to."),
        SOUP(Items.MUSHROOM_SOUP, Characteristic.SOUP, "When enabled, players will", "receive the amount of health", "defined in config when", "right-clicking a soup."),
        SUMO(Material.SLIME_BALL, Characteristic.SUMO, "When enabled, players will ", "lose health only when", "interacting with water or lava."),
        UHC(Material.GOLDEN_APPLE, Characteristic.UHC, "When enabled, player's health", "will not naturally regenerate."),
        COMBO(Material.IRON_SWORD, Characteristic.COMBO, "When enabled, players will", "have no delay between hits.");

        @Getter
        private final Material displayed;
        @Getter
        private final String[] description;

        private final Function<KitImpl, Boolean> getter;
        private final Consumer<KitImpl> setter;

        Option(final Material displayed, final Function<KitImpl, Boolean> getter, final Consumer<KitImpl> setter, final String... description) {
            this.displayed = displayed;
            this.description = description;
            this.getter = getter;
            this.setter = setter;
        }

        Option(final Material displayed, final Characteristic characteristic, final String... description) {
            this.displayed = displayed;
            this.description = description;
            this.getter = kit -> kit.hasCharacteristic(characteristic);
            this.setter = kit -> kit.toggleCharacteristic(characteristic);
        }

        public boolean get(final KitImpl kit) {
            return getter.apply(kit);
        }

        public void set(final KitImpl kit) {
            setter.accept(kit);
        }
    }
}
