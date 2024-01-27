package me.realized.duels.command.commands.duels.subcommands;

import java.util.List;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.command.BaseCommand;
import me.realized.duels.kit.KitImpl;
import me.realized.duels.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoadkitCommand extends BaseCommand {

    public LoadkitCommand(final DuelsPlugin plugin) {
        super(plugin, "loadkit", "loadkit [name]", "Loads the selected kit to your inventory.", 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final String name = StringUtil.join(args, " ", 1, args.length).replace("-", " ");
        final KitImpl kit = kitManager.get(name);

        if (kit == null) {
            lang.sendMessage(sender, "ERROR.kit.not-found", "name", name);
            return;
        }

        final Player player = (Player) sender;
        player.getInventory().clear();
        kit.equip(player);
        lang.sendMessage(sender, "COMMAND.duels.load-kit", "name", name);
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 2) {
            return handleTabCompletion(args[1], kitManager.getNames(false));
        }

        return null;
    }
}
