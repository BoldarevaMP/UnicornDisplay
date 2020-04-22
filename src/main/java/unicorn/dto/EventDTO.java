package unicorn.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {

    private Integer id;

    private String name;

    private String treatmentName;

    private Integer dosage;

    private String dosageForm;

    private String date;

    private String status;

    private String comment;
}
