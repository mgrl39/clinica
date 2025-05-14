package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Repository.DentistRepository;
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
    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil = new JwtUtil();


    public Long edit(@RequestBody Dentist dentist) {
        return 0L;
    }

    @PostMapping("login/dentist")
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

    @PostMapping("/register")
    public Dentist register(@RequestBody Dentist dentist, @RequestBody String confirmPass) throws NoSuchAlgorithmException, KeyStoreException {
        //Comprobar si las contraseñas son iguales
        if(dentist.getPassword().equals(confirmPass)) {
            //Setear la contraseña del user hasheada y guardarlo en la DB
            dentist.setPassword(passwordEncoder.encode(dentist.getPassword()));
            dentistRepository.save(dentist);
            return dentist;
        }
        return null;
    }


    public List<Dentist> show() {
        return null;
    }
}
