package peaksoft.appplaza.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private Status status;
    private boolean subscribe;
    private LocalDate localDate;

}
