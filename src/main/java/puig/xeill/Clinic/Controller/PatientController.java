package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/get")
    public Page<Patient> getPatients (@RequestParam int page) {
        return patientRepository.findAll(Pageable.ofSize(10).withPage(page));
    }

    @PostMapping("/create")
    public Patient create(@RequestBody Patient patient) {
        patient.setName(passwordEncoder.encode(patient.getName()));
        patient.setDni(passwordEncoder.encode(patient.getDni()));
        patientRepository.save(patient);

        return null;
        //return null;
    }

}