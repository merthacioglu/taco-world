package org.mhacioglu.tacoworld.messaging;

import org.mhacioglu.tacoworld.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
