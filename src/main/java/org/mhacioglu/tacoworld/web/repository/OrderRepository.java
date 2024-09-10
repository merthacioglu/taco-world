package org.mhacioglu.tacoworld.web.repository;

import org.mhacioglu.tacoworld.web.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
