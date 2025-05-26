package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Model.Request.VisitRequest;
import puig.xeill.Clinic.Model.Visit;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Repository.VisitRepository;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("visits")
public class VisitController {
    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> show() {
        return null;
    }
    public String edit(@RequestBody Visit visit) {
        return null;
    }

    @PostMapping("create")
    public Long create(@RequestBody VisitRequest visitRequest, @RequestHeader String token) throws Exception {

        String name = jwtUtil.getNameFromToken(token);
        Optional<Patient> patientOptional = patientRepository.findByDni(Security.encrypt(visitRequest.getDni()));
        Optional<Dentist> dentistOptional;
        System.out.println(token);
        System.out.println(patientOptional.get().getName());
        if(visitRequest.getId_dentist() == null){
            System.out.println(name);
            dentistOptional = dentistRepository.findByUser(name);
        }
        else {
            dentistOptional = dentistRepository.findById(visitRequest.getId_dentist());
        }
        //if (dentistOptional.isPresent()){
            System.out.println(dentistOptional.get().getId());
            System.out.println(dentistOptional.get().getName());
        //}


        Visit visit = new Visit();

        if(dentistOptional.isPresent() && patientOptional.isPresent()) {

            Dentist dentist = dentistOptional.get();
            Patient patient = patientOptional.get();
            visit.setIdPatient(patient);
            visit.setIdDentist(dentist);
            visit.setDate(visitRequest.getDate());
            System.out.println("-----------------");
            System.out.println(visitRequest.getDate());
            visit.setReason(visitRequest.getReason());
            visit.setComment(visitRequest.getComment());
            visit.setTime(visitRequest.getTime());
        }
        System.out.println(visit.toString());
        visitRepository.save(visit);
        return visit.getId();
    }
}
