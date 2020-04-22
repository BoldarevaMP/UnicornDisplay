package unicorn.service;

import unicorn.dto.EventDTO;
import unicorn.webService.WebService;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Singleton
@ApplicationScoped
public class EventServiceImpl implements EventService {

    @Inject
    private WebService webService;

    @Override
    public List<EventDTO> getEvents() {
        return webService.getEvents();
    }
}
