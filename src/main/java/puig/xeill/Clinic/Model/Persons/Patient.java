package puig.xeill.Clinic.Model.Persons;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puig.xeill.Clinic.Model.Enums.BloodType;
import puig.xeill.Clinic.Model.Enums.TypeOfPatient;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient extends Person{
    private Date bornDate;
    private String dni;
    private TypeOfPatient type;
    private BloodType blood;
}
