package unicorn.backingBean;

import lombok.Getter;
import lombok.Setter;
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

    @Inject
    private EventService eventService;

    public String getCDI_PUSH_TOPIC() {
        return CDI_PUSH_TOPIC;
    }

    private List<EventDTO> eventDTOS;

    private final String CDI_PUSH_TOPIC = "pushCdi";
    private TopicKey topicKey = new TopicKey(CDI_PUSH_TOPIC);
    private TopicsContext topicsContext;

    @PostConstruct
    public void initDisplay() {
        eventDTOS = new ArrayList<>();
        eventDTOS.addAll(eventService.getEvents());
        logger.info("Successfully Init Storage, size " + eventDTOS.size());

        topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(topicKey);
    }

    @Override
    public void updateEventList(List<EventDTO> eventDTOList) {
        eventDTOS = eventDTOList;
        logger.info("Storage Successfully Updated!");
        sendUpdate();
    }

    private void sendUpdate() {
        try {
            topicsContext.publish(topicKey, eventDTOS);
        } catch (MessageException ex) {
            logger.debug("List is not updated!");
        }
    }

    public List<EventDTO> getEventDTOS() {
        return eventDTOS;
    }
}

