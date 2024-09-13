package org.mhacioglu.tacoworld.repository;
import java.util.Date;
import java.util.List;
import org.mhacioglu.tacoworld.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date from, Date to
    );





}
