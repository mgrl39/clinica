package puig.xeill.Clinic.Model;

import jakarta.persistence.*;
import lombok.*;
import puig.xeill.Clinic.Model.Enums.VisitReason;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private VisitReason reason;
    private String comment;
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_patient")
    private Patient idPatient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_dentist")
    private Dentist idDentist;

    private LocalDate time;
}
