package puig.xeill.Clinic.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import puig.xeill.Clinic.Model.Persons.Dentist;

import java.util.List;

public class DentistController {

    public Long edit(@RequestBody Dentist dentist) {
        return 0L;
    }
    public Long create(@RequestBody Dentist dentist) {
        return 0L;
    }
    public List<Dentist> show() {
        return null;
    }
}
