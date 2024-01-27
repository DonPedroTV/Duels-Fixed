package me.fixed.duels.hook;

import me.fixed.duels.util.hook.AbstractHookManager;
import me.fixed.duels.DuelsPlugin;
import me.fixed.duels.hook.hooks.BountyHuntersHook;
import me.fixed.duels.hook.hooks.CombatLogXHook;
import me.fixed.duels.hook.hooks.CombatTagPlusHook;
import me.fixed.duels.hook.hooks.EssentialsHook;
import me.fixed.duels.hook.hooks.FactionsHook;
import me.fixed.duels.hook.hooks.LeaderHeadsHook;
import me.fixed.duels.hook.hooks.MVdWPlaceholderHook;
import me.fixed.duels.hook.hooks.McMMOHook;
import me.fixed.duels.hook.hooks.MyPetHook;
import me.fixed.duels.hook.hooks.PlaceholderHook;
import me.fixed.duels.hook.hooks.PvPManagerHook;
import me.fixed.duels.hook.hooks.SimpleClansHook;
import me.fixed.duels.hook.hooks.VaultHook;
import me.fixed.duels.hook.hooks.worldguard.WorldGuardHook;

public class HookManager extends AbstractHookManager<DuelsPlugin> {

    public HookManager(final DuelsPlugin plugin) {
        super(plugin);
        register(BountyHuntersHook.NAME, BountyHuntersHook.class);
        register(CombatLogXHook.NAME, CombatLogXHook.class);
        register(CombatTagPlusHook.NAME, CombatTagPlusHook.class);
        register(EssentialsHook.NAME, EssentialsHook.class);
        register(FactionsHook.NAME, FactionsHook.class);
        register(LeaderHeadsHook.NAME, LeaderHeadsHook.class);
        register(McMMOHook.NAME, McMMOHook.class);
        register(MVdWPlaceholderHook.NAME, MVdWPlaceholderHook.class);
        register(MyPetHook.NAME, MyPetHook.class);
        register(PlaceholderHook.NAME, PlaceholderHook.class);
        register(PvPManagerHook.NAME, PvPManagerHook.class);
        register(SimpleClansHook.NAME, SimpleClansHook.class);
        register(VaultHook.NAME, VaultHook.class);
        register(WorldGuardHook.NAME, WorldGuardHook.class);
    }
}
