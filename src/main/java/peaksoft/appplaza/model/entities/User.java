package peaksoft.appplaza.model.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int age;
    private boolean subscribe;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate localDate;

}
