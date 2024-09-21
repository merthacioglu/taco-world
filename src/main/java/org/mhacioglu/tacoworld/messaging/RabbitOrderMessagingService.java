package org.mhacioglu.tacoworld.messaging;

import org.mhacioglu.tacoworld.model.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, messageProperties);
        rabbitTemplate.send("tacoworld.order", message);
    }

    @Override
    public void convertAndSendOrder(TacoOrder order){
        rabbitTemplate.convertAndSend("tacoworld.order", order,
                message -> {
                    MessageProperties props = message.getMessageProperties();
                    props.setHeader("X_ORDER_SOURCE", "WEB");
                    return message;
                });
    }


}
