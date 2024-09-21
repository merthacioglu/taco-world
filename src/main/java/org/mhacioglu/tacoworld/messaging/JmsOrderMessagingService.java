package org.mhacioglu.tacoworld.messaging;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.mhacioglu.tacoworld.model.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jmsTemplate;
    private Destination dest;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate,
                                    Destination dest) {
        this.jmsTemplate = jmsTemplate;
        this.dest = dest;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(dest, session -> {
            Message msg = session.createObjectMessage(order);
            msg.setStringProperty("X_ORDER_SOURCE", "WEB");
            return msg;
        });
    }

    /*@Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(
                "tacocloud.order.queue",
                session -> session.createObjectMessage(order));
    }*/
    @Override
    public void convertAndSendOrder(TacoOrder order) {
        jmsTemplate.convertAndSend("tacoworldorder.queue", order,
                this::addOrderSource);
    }

    private Message addOrderSource(Message msg) throws JMSException {
        msg.setStringProperty("X_ORDER_SOURCE", "WEB");
        return msg;
    }
}
