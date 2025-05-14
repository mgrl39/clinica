package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.SpecialtyRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("dentists")
public class DentistController {

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil = new JwtUtil();


    public Long edit(@RequestBody Dentist dentist) {
        return 0L;
    }

    @PostMapping("login")
    public String loginDentist(@RequestBody Dentist user) throws NoSuchAlgorithmException, KeyStoreException {

        Optional<Dentist> dentistOptional = dentistRepository.findByUser(user.getUser());

        if (!dentistOptional.isPresent()) {
            return null;
        }

        if (passwordEncoder.matches(user.getPassword(), dentistOptional.get().getPassword())) {
            String token = jwtUtil.generateToken(user.getUser());
            return token;
        }
        return null;
    }

    @PostMapping("/create")
    public Dentist register(@RequestBody Dentist dentist) throws NoSuchAlgorithmException, KeyStoreException {
            dentist.setPassword(passwordEncoder.encode(dentist.getPassword()));
            //List<Specialty> specialties = specialtyRepository.findAllById(dentist.getSpecialties().get().getId());
            dentistRepository.save(dentist);
            return dentist;
    }


    public List<Dentist> show() {
        return null;
    }
}
