package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Model.Persons.User;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.lang.reflect.Array;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    AdminController adminController;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("login")
    public String loginAndCHeckUser(@RequestBody User user) throws NoSuchAlgorithmException, KeyStoreException {

        Optional<Dentist> dentistOptional = dentistRepository.findByUser(user.getName());

        if(!dentistOptional.isPresent()) {
            Optional<Admin> optionalAdmin = adminRepository.findByUser(user.getName());

            if (!optionalAdmin.isPresent()) {
                //String [] data = {"Error","User not found"};

            }
            else {
                if(passwordEncoder.matches(user.getPassword(), optionalAdmin.get().getPassword())){
                    String token = jwtUtil.generateToken(user.getUser());
                    return token;
                }

            }
            return null;
        }


        return null;
    }
}
