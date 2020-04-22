package unicorn.service;

import unicorn.dto.EventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> getEvents();
}
