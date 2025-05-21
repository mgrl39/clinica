package puig.xeill.Clinic.Model.Request;

import lombok.*;
import puig.xeill.Clinic.Model.Enums.VisitReason;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VisitRequest {

    private Long id;
    private VisitReason reason;
    private String comment;
    private Date date;
    private String dni;
    private Long id_dentist;
    private LocalDate time;
}
