package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("patients")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil = new JwtUtil();

    @GetMapping("/show/{id}")
    public Optional<Patient> show(@PathVariable Long id) {

        System.out.println(id);

        Optional<Patient> patient = patientRepository.findById(id);
        System.out.println(new Date());
        if (patient.isPresent()) return patient;
        else return null;
        //return null;
    }

    @PostMapping("/create")
    public Patient show(@RequestBody Patient patient) {

        patientRepository.save(patient);

        return null;
        //return null;
    }

}