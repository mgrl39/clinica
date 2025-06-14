package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Repository.ScheduleRepository;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("admins")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DentistRepository dentistRepository;


    @Autowired
    PasswordEncoder passwordEncoder;



    //@Autowired
    JwtUtil jwtUtil = new JwtUtil();

    @GetMapping("/show/{id}")
    public Optional<Admin> show(@PathVariable Long id) {

        System.out.println(id);
        Optional<Admin> admin = adminRepository.findById(id);
        System.out.println(new Date());
        //admin.get().setUser(passwordEncoder.de);
        return admin;
        //return null;
    }

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) throws Exception {

        Optional<Admin> adminOptional = adminRepository.findByUser(admin.getUser());
        if(!adminOptional.isPresent()){
            admin.setName(Security.encrypt(admin.getName()));
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);
        }
        return null;
    }
}
