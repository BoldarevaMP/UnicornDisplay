package unicorn.bean;

import org.apache.log4j.Logger;
import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import unicorn.dto.EventDTO;
import unicorn.service.EventService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for index.xhtml page
 */

@ApplicationScoped
@ManagedBean(name = "display")
public class DisplayBeanImpl implements DisplayBean {

    private static final Logger logger = Logger.getLogger(DisplayBeanImpl.class);

    private List<EventDTO> eventDTOS;

    private final String CDI_PUSH_TOPIC = "pushCdi";
    private TopicKey topicKey = new TopicKey(CDI_PUSH_TOPIC);
    private TopicsContext topicsContext;

    @Inject
    private EventService eventService;

    /**
     * Initializes data when the app is loading
     */

    @PostConstruct
    public void initDisplay() {
        eventDTOS = new ArrayList<>();
        eventDTOS.addAll(eventService.getEvents());
        logger.info("Successfully Init Storage, size " + eventDTOS.size());

        topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(topicKey);
    }

    /**
     * Updates data in list of events
     *
     * @param eventDTOList
     */

    @Override
    public void updateEventList(List<EventDTO> eventDTOList) {
        eventDTOS = eventDTOList;
        logger.info("List of events is successfully updated");
        sendUpdate();
    }

    public List<EventDTO> getEventDTOS() {
        return eventDTOS;
    }

    public String getCdiPushTopic() {
        return CDI_PUSH_TOPIC;
    }

    private void sendUpdate() {
        try {
            topicsContext.publish(topicKey, eventDTOS);
        } catch (MessageException ex) {
            logger.error("Exception: List is not updated");
        }
    }


}

