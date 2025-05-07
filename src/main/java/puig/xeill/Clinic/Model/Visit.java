package puig.xeill.Clinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Enums.VisitReason;
import puig.xeill.Clinic.Model.Persons.Dentist;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visits")
public class Visit {
    private Long id;
    private VisitReason reason;
    private String comment;
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id", table = "patients")
    private long idPatient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id", table = "dentists")
    private long id_dentist;
}
