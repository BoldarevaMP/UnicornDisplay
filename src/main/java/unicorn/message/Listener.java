package unicorn.message;


import org.apache.log4j.Logger;


//import app.backingBeans.StandBean;
//import app.services.ProductService;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/DLQ"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class Listener implements MessageListener {

    private static final Logger logger = Logger.getLogger(Listener.class);

    //public static List<String> messages = new ArrayList<String>();

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage mes = (TextMessage)message;
            //считываем свойство из соответствующего поля, заданное вручную в consumer
            System.out.println("FROM MDB - client type IS " + message.getStringProperty("clientType"));
            //считываем  само сообщение
            System.out.println("FROM MDB - payload  IS" + mes.getText());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    }
