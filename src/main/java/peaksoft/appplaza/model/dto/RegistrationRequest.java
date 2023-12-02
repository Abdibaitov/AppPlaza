package peaksoft.appplaza.model.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;

@Getter
@Setter
public class RegistrationRequest {
    private String name;
    private String lastName;
    private String email;
    private int age;
    private boolean subscribe;

}
