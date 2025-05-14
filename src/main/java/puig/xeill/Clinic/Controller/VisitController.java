package puig.xeill.Clinic.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Model.Visit;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.util.List;
import java.util.Optional;

public class VisitController {
    JwtUtil jwtUtil = new JwtUtil();

    DentistRepository dentistRepository;
    PatientRepository patientRepository;

    public List<Visit> show() {
        return null;
    }
    public String edit(@RequestBody Visit visit) {
        return null;
    }

    public Long create(@RequestBody Visit visit,@RequestBody String dni, @RequestHeader String token) {

        String name = jwtUtil.getNameFromToken(token);

        Optional<Dentist> dentistOptional = dentistRepository.findByUser(name);
        Optional<Patient> patientOptional = patientRepository.findByDni(dni);

        if(dentistOptional.isPresent() && patientOptional.isPresent()) {
            Dentist dentist = dentistOptional.get();
            visit.setId_dentist(dentist);
        }


        return 0L;
    }
}
