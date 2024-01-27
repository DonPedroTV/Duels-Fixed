package me.fixed.duels.command.commands.duels;

import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.Permissions;
import me.fixed.duels.command.BaseCommand;
import me.fixed.duels.command.commands.duels.subcommands.AddsignCommand;
import me.fixed.duels.command.commands.duels.subcommands.BindCommand;
import me.fixed.duels.command.commands.duels.subcommands.CreateCommand;
import me.fixed.duels.command.commands.duels.subcommands.CreatequeueCommand;
import me.fixed.duels.command.commands.duels.subcommands.DeleteCommand;
import me.fixed.duels.command.commands.duels.subcommands.DeletekitCommand;
import me.fixed.duels.command.commands.duels.subcommands.DeletequeueCommand;
import me.fixed.duels.command.commands.duels.subcommands.DeletesignCommand;
import me.fixed.duels.command.commands.duels.subcommands.EditCommand;
import me.fixed.duels.command.commands.duels.subcommands.HelpCommand;
import me.fixed.duels.command.commands.duels.subcommands.InfoCommand;
import me.fixed.duels.command.commands.duels.subcommands.ListCommand;
import me.fixed.duels.command.commands.duels.subcommands.LoadkitCommand;
import me.fixed.duels.command.commands.duels.subcommands.LobbyCommand;
import me.fixed.duels.command.commands.duels.subcommands.OptionsCommand;
import me.fixed.duels.command.commands.duels.subcommands.PlaysoundCommand;
import me.fixed.duels.command.commands.duels.subcommands.ReloadCommand;
import me.fixed.duels.command.commands.duels.subcommands.ResetCommand;
import me.fixed.duels.command.commands.duels.subcommands.ResetratingCommand;
import me.fixed.duels.command.commands.duels.subcommands.SavekitCommand;
import me.fixed.duels.command.commands.duels.subcommands.SetCommand;
import me.fixed.duels.command.commands.duels.subcommands.SetitemCommand;
import me.fixed.duels.command.commands.duels.subcommands.SetlobbyCommand;
import me.fixed.duels.command.commands.duels.subcommands.SetratingCommand;
import me.fixed.duels.command.commands.duels.subcommands.TeleportCommand;
import me.fixed.duels.command.commands.duels.subcommands.ToggleCommand;
import org.bukkit.command.CommandSender;

public class DuelsCommand extends BaseCommand {

    public DuelsCommand(final DuelsPlugin plugin) {
        super(plugin, "duels", Permissions.ADMIN, false);
        child(
            new HelpCommand(plugin),
            new SavekitCommand(plugin),
            new DeletekitCommand(plugin),
            new LoadkitCommand(plugin),
            new SetitemCommand(plugin),
            new OptionsCommand(plugin),
            new BindCommand(plugin),
            new CreateCommand(plugin),
            new DeleteCommand(plugin),
            new SetCommand(plugin),
            new ToggleCommand(plugin),
            new TeleportCommand(plugin),
            new CreatequeueCommand(plugin),
            new DeletequeueCommand(plugin),
            new AddsignCommand(plugin),
            new DeletesignCommand(plugin),
            new SetlobbyCommand(plugin),
            new LobbyCommand(plugin),
            new InfoCommand(plugin),
            new ListCommand(plugin),
            new EditCommand(plugin),
            new SetratingCommand(plugin),
            new ResetCommand(plugin),
            new ResetratingCommand(plugin),
            new PlaysoundCommand(plugin),
            new ReloadCommand(plugin)
        );
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        lang.sendMessage(sender, "COMMAND.duels.usage", "command", label);
    }
}
