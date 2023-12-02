package peaksoft.appplaza.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;
@Getter
@Setter
@Builder
public class RegistrationResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private Status status;
    private String response;

}
