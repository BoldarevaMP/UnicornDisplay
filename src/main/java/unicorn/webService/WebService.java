package unicorn.webService;

import unicorn.dto.EventDTO;

import java.util.List;

public interface WebService {

    List<EventDTO> getEvents();
}
