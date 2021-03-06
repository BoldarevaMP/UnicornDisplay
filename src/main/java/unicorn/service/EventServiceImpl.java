package unicorn.service;

import unicorn.dto.EventDTO;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of service for event handling
 */

@Singleton
@ApplicationScoped
public class EventServiceImpl implements EventService {

    @Inject
    private WebService webService;

    /**
     * Provides list of events
     */

    @Override
    public List<EventDTO> getEvents() {
        return webService.getEvents();
    }
}
