package puig.xeill.Clinic.Model.Persons;

import jakarta.persistence.*;
import lombok.*;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Model.Visit;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "dentists")
public class Dentist {
    @Id
    private Long id;

    private Long idSchedule;
    private String user;
    private String password;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "dentist_specialties",
            joinColumns = @JoinColumn(name = "dentist_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties;
}
