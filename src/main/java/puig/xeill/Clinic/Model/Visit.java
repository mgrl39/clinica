package puig.xeill.Clinic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Enums.VisitReason;

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
    private long idPatient;
    private long idDentist;
}
