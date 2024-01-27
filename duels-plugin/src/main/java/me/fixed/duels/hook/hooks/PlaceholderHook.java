package me.fixed.duels.hook.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.fixed.duels.util.hook.PluginHook;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.data.UserData;
import me.fixed.duels.data.UserManagerImpl;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PluginHook<DuelsPlugin> {

    public static final String NAME = "PlaceholderAPI";

    private final UserManagerImpl userDataManager;

    public PlaceholderHook(final DuelsPlugin plugin) {
        super(plugin, NAME);
        this.userDataManager = plugin.getUserManager();
        new Placeholders().register();
    }

    public class Placeholders extends PlaceholderExpansion {

        @Override
        public String getIdentifier() {
            return "duels";
        }

        @Override
        public String getAuthor() {
            return "Realized & DonPedroTV & Community";
        }

        @Override
        public String getVersion() {
            return "1.0";
        }

        @Override
        public boolean persist() {
            return true;
        }

        @Override
        public String onPlaceholderRequest(final Player player, final String identifier) {
            if (player == null) {
                return "Player is required";
            }

            final UserData user = userDataManager.get(player);

            if (user == null) {
                return null;
            }

            switch (identifier) {
                case "wins":
                    return String.valueOf(user.getWins());
                case "losses":
                    return String.valueOf(user.getLosses());
                case "can_request":
                    return String.valueOf(user.canRequest());
            }

            return null;
        }
    }
}
