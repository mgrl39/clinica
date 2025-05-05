package puig.xeill.Clinic.Model.Persons;

import puig.xeill.Clinic.Model.Enums.BloodType;
import puig.xeill.Clinic.Model.Enums.TypeOfPatient;

import java.util.Date;

public class Patient extends Person{
    private Date bornDate;
    private String dni;
    private TypeOfPatient type;
    private BloodType blood;
}
