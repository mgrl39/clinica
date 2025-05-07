package puig.xeill.Clinic.Model.Persons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Model.Visit;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dentists")
public class Dentist extends User {
    private Long idShedule;

    @ManyToMany
    @JoinTable(
            name = "dentist_specialties",
            joinColumns = @JoinColumn(name = "dentist_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties;
}
