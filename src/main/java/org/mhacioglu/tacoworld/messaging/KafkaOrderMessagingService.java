package org.mhacioglu.tacoworld.messaging;

import org.mhacioglu.tacoworld.model.TacoOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;


    public KafkaOrderMessagingService
            (KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.send("tacoworld.orders.topic", order);
    }
}
