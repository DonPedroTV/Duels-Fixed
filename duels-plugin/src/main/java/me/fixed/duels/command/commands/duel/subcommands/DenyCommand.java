package me.fixed.duels.command.commands.duel.subcommands;

import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.request.RequestImpl;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.api.event.request.RequestDenyEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DenyCommand extends BaseCommand {

    public DenyCommand(final DuelsPlugin plugin) {
        super(plugin, "deny", "deny [player]", "Declines a duel request.", 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final Player player = (Player) sender;
        final Player target = Bukkit.getPlayerExact(args[1]);

        if (target == null || !player.canSee(target)) {
            lang.sendMessage(sender, "ERROR.player.not-found", "name", args[1]);
            return;
        }

        final RequestImpl request;

        if ((request = requestManager.remove(target, player)) == null) {
            lang.sendMessage(sender, "ERROR.duel.no-request", "name", target.getName());
            return;
        }

        final RequestDenyEvent event = new RequestDenyEvent(player, target, request);
        Bukkit.getPluginManager().callEvent(event);

        lang.sendMessage(player, "COMMAND.duel.request.deny.receiver", "name", target.getName());
        lang.sendMessage(target, "COMMAND.duel.request.deny.sender", "name", player.getName());
    }
}
