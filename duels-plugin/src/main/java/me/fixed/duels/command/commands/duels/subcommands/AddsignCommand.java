package me.fixed.duels.command.commands.duels.subcommands;

import java.util.Arrays;
import java.util.List;

import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.queue.Queue;
import me.fixed.duels.util.NumberUtil;
import me.fixed.duels.util.StringUtil;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.kit.KitImpl;
import me.fixed.duels.util.BlockUtil;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddsignCommand extends BaseCommand {

    public AddsignCommand(final DuelsPlugin plugin) {
        super(plugin, "addsign", "addsign [bet] [kit:-]", "Creates a queue sign with given bet and kit.", 3, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final Player player = (Player) sender;
        final Sign sign = BlockUtil.getTargetBlock(player, Sign.class, 6);

        if (sign == null) {
            lang.sendMessage(sender, "ERROR.sign.not-a-sign");
            return;
        }

        final int bet = NumberUtil.parseInt(args[1]).orElse(0);
        KitImpl kit = null;

        if (!args[2].equals("-")) {
            String name = StringUtil.join(args, " ", 2, args.length).replace("-", " ");
            kit = kitManager.get(name);

            if (kit == null) {
                lang.sendMessage(sender, "ERROR.kit.not-found", "name", name);
                return;
            }
        }

        final String kitName = kit != null ? kit.getName() : lang.getMessage("GENERAL.none");
        final Queue queue = queueManager.get(kit, bet);

        if (queue == null) {
            lang.sendMessage(sender, "ERROR.queue.not-found", "bet_amount", bet, "kit", kitName);
            return;
        }

        if (!queueSignManager.create(player, sign.getLocation(), queue)) {
            lang.sendMessage(sender, "ERROR.sign.already-exists");
            return;
        }

        final Location location = sign.getLocation();
        lang.sendMessage(sender, "COMMAND.duels.add-sign", "location", StringUtil.parse(location), "kit", kitName, "bet_amount", bet);
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 2) {
            return Arrays.asList("0", "10", "50", "100", "500", "1000");
        }

        if (args.length > 2) {
            return handleTabCompletion(args[2], kitManager.getNames(true));
        }

        return null;
    }
}
