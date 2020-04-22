package unicorn.message;


import org.apache.log4j.Logger;
import unicorn.backingBean.DisplayBean;
import unicorn.service.EventService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/DLQ"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class Listener implements MessageListener {

    private static final Logger logger = Logger.getLogger(Listener.class);

    @Inject
    private DisplayBean displayBean;
    @Inject
    private EventService eventService;

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getBody(String.class).equals("Update")) {
                displayBean.updateEventList(eventService.getEvents());
            }
        } catch (JMSException e) {
            logger.error("Catch Exception");
        }
    }
}
