package unicorn.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Event DTO class
 */

@Getter
@Setter
public class EventDTO {

    private Integer id;

    private String patientLastName;

    private String treatmentName;

    private Integer dosage;

    private String dosageForm;

    private String date;

    private String status;

    private String comment;
}
