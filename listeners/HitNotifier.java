package listeners;

import listeners.HitListener;

/**
 * Created by haim on 08-May-16.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
