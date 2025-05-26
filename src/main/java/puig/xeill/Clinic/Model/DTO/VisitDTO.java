package puig.xeill.Clinic.Model.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class VisitDTO {
    private Long id;
    private String reason;
    private String comment;
    private Date date;
    private Long idPatient;
    private Long idDentist;
}
