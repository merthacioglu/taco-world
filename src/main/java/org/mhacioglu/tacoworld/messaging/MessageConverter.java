package org.mhacioglu.tacoworld.messaging;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.messaging.converter.MessageConversionException;

public interface MessageConverter {
    Message toMessage(Object obj, Session session) throws JMSException
            ,MessageConversionException;

    Object fromMessage(Message message);


}