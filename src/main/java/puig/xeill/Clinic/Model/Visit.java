package puig.xeill.Clinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Enums.VisitReason;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visits")
public class Visit {
    @Id
    private Long id;

    private VisitReason reason;
    private String comment;
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id")
    private Patient idPatient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dentists_id")
    private Dentist id_dentist;
}
