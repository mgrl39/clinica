package puig.xeill.Clinic.Model.DTO;

import jakarta.persistence.*;
import lombok.*;
import puig.xeill.Clinic.Model.Specialty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DentistDTO {

    private Long idSchedule;
    private String user;
    private String password;
    private String name;
    private List<Long> specialties;
}
