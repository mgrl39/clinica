package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.SpecialtyRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
    public DentistDTO register(@RequestBody DentistDTO dentistDTO) {

            dentistDTO.setPassword(passwordEncoder.encode(dentistDTO.getPassword()));
            List<Specialty> specialties = new ArrayList<Specialty>();

            dentistDTO.getSpecialties().forEach(specialtyID -> {
                Optional<Specialty> specialtyOptional = specialtyRepository.findById(specialtyID);

                Specialty specialty = specialtyOptional.get();
                specialties.add(specialty);

            });

            Dentist dentist = new Dentist();
            dentist.setUser(dentistDTO.getUser());
            dentist.setName(dentistDTO.getName());
            dentist.setPassword(dentistDTO.getPassword());
            dentist.setIdSchedule(dentistDTO.getIdSchedule());
            dentist.setSpecialties(specialties);
            System.out.println(dentist.getSpecialties());
            dentistRepository.save(dentist);
            return dentistDTO;
    }


    public List<Dentist> show() {
        return null;
    }
}
