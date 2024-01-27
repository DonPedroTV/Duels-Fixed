package me.fixed.duels.command.commands.queue.subcommands;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.Permissions;
import me.fixed.duels.command.BaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand extends BaseCommand {

    public LeaveCommand(final DuelsPlugin plugin) {
        super(plugin, "leave", null, null, Permissions.QUEUE, 1, true, "l");
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        if (queueManager.remove((Player) sender) == null) {
            lang.sendMessage(sender, "ERROR.queue.not-in-queue");
        }
    }
}
