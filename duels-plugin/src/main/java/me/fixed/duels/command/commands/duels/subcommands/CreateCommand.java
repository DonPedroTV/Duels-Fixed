package me.fixed.duels.command.commands.duels.subcommands;

import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.util.StringUtil;
import me.fixed.duels.DuelsPlugin;
import org.bukkit.command.CommandSender;

public class CreateCommand extends BaseCommand {

    public CreateCommand(final DuelsPlugin plugin) {
        super(plugin, "create", "create [name]", "Creates an arena with given name.", 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final String name = StringUtil.join(args, " ", 1, args.length);

        if (!StringUtil.isAlphanumeric(name)) {
            lang.sendMessage(sender, "ERROR.command.name-not-alphanumeric", "name", name);
            return;
        }

        if (!arenaManager.create(sender, name)) {
            lang.sendMessage(sender, "ERROR.arena.already-exists", "name", name);
            return;
        }

        lang.sendMessage(sender, "COMMAND.duels.create", "name", name);
    }
}
