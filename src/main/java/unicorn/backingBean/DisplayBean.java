package unicorn.backingBean;

import unicorn.dto.EventDTO;

import java.util.List;

public interface DisplayBean {
    void updateEventList(List<EventDTO> eventDTOList);
}
