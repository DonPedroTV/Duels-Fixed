package me.realized.duels.api.spectate;

import java.util.List;
import me.realized.duels.api.arena.Arena;
import me.realized.duels.api.event.spectate.SpectateEndEvent;
import me.realized.duels.api.event.spectate.SpectateStartEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the SpectateManager singleton used by Duels.
 *
 * @since 3.4.1
 */
public interface SpectateManager {

    /**
     * Attempts to find a {@link Spectator} instance associated to the player given.
     *
     * @param player Player to search through the spectator cache. Should not be null!
     * @return {@link Spectator} instance associated to player if exists or null otherwise.
     */
    @Nullable
    Spectator get(@NotNull final Player player);


    /**
     * Checks if a player is spectating a match.
     *
     * @param player Player to check if spectating. Should not be null!
     * @return true if player is spectating or false otherwise.
     */
    boolean isSpectating(@NotNull final Player player);


    /**
     * Attempts to put the player in spectator mode and teleports player to target player in match.
     *
     * The method will return a result other than Result.SUCCESS if:
     * - Player is already in spectator mode
     * - Player is in a queue
     * - Player is in a match
     * - Target is not in a match
     * - {@link SpectateStartEvent} is cancelled
     *
     * Note: Calls {@link SpectateStartEvent} before player is turned into spectator mode.
     *
     * @param player Player to put in spectator mode. Should not be null!
     * @param target Target player to teleport to. Should not be null!
     * @return Never-null {@link Result} instance indicating the outcome of this call.
     */
    @NotNull
    Result startSpectating(@NotNull final Player player, @NotNull final Player target);


    /**
     * Puts a player out of spectator mode.
     *
     * Note: Calls {@link SpectateEndEvent} after player stops spectating.
     *
     * @param player Player to stop spectating. Should not be null!
     */
    void stopSpectating(@NotNull final Player player);


    /**
     * An UnmodifiableList of {@link Spectator}s that are currently spectating the given {@link Arena}.
     *
     * @param arena {@link Arena} to get spectators
     * @return Never-null UnmodifiableList of {@link Spectator}s spectating given {@link Arena}
     */
    @NotNull
    List<Spectator> getSpectators(@NotNull final Arena arena);


    enum Result {

        ALREADY_SPECTATING,
        IN_QUEUE,
        IN_MATCH,
        TARGET_NOT_IN_MATCH,
        EVENT_CANCELLED,
        SUCCESS;

    }
}
