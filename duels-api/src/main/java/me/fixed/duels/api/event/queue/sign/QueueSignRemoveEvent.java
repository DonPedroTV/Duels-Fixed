package me.fixed.duels.api.event.queue.sign;

import me.fixed.duels.api.queue.sign.QueueSign;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a {@link QueueSign} is removed.
 *
 * @see QueueSign#isRemoved()
 * @since 3.2.0
 */
public class QueueSignRemoveEvent extends QueueSignEvent {

    private static final HandlerList handlers = new HandlerList();

    public QueueSignRemoveEvent(@NotNull final Player source, @NotNull final QueueSign queueSign) {
        super(source, queueSign);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}