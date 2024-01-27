package me.fixed.duels.command.commands.duel.subcommands;

import java.util.UUID;

import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.gui.inventory.InventoryGui;
import me.fixed.duels.util.UUIDUtil;
import me.fixed.duels.DuelsPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand extends BaseCommand {

    public InventoryCommand(final DuelsPlugin plugin) {
        super(plugin, "_", "_ [uuid]", "Displays player's inventories after match.", 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final UUID target = UUIDUtil.parseUUID(args[1]);

        if (target == null) {
            lang.sendMessage(sender, "ERROR.inventory-view.not-a-uuid", "input", args[1]);
            return;
        }

        final InventoryGui gui = inventoryManager.get(UUID.fromString(args[1]));

        if (gui == null) {
            lang.sendMessage(sender, "ERROR.inventory-view.not-found", "uuid", target);
            return;
        }

        gui.open((Player) sender);
    }
}
