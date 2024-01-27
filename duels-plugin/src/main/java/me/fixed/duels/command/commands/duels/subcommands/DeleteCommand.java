package me.fixed.duels.command.commands.duels.subcommands;

import java.util.List;

import me.fixed.duels.arena.ArenaImpl;
import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.util.StringUtil;
import me.fixed.duels.DuelsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends BaseCommand {

    public DeleteCommand(final DuelsPlugin plugin) {
        super(plugin, "delete", "delete [name]", "Deletes an arena.", 2, false);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final String name = StringUtil.join(args, " ", 1, args.length).replace("-", " ");
        final ArenaImpl arena = arenaManager.get(name);

        if (arena == null) {
            lang.sendMessage(sender, "ERROR.arena.not-found", "name", name);
            return;
        }

        if (arena.isUsed()) {
            lang.sendMessage(sender, "ERROR.arena.delete-failure", "name", name);
            return;
        }

        arenaManager.remove(sender, arena);
        lang.sendMessage(sender, "COMMAND.duels.delete", "name", name);
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 2) {
            return handleTabCompletion(args[1], arenaManager.getNames());
        }

        return null;
    }
}
