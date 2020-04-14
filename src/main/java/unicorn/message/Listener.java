package unicorn.message;


import org.apache.log4j.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/DLQ"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class Listener implements MessageListener {

    private static final Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getBody(String.class).equals("Update")) {
                logger.info(message.getBody(String.class));
            }
        } catch (JMSException e) {
            logger.error("Catch Exception");
        }
    }
}
