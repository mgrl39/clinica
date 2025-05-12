package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Repository.ScheduleRepository;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("admins")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DentistRepository dentistRepository;



    @GetMapping("/show/{id}")
    public Optional<Admin> show(@PathVariable Long id) {

        System.out.println(id);
        Optional<Admin> admin = adminRepository.findById(id);
        System.out.println(new Date());
        return admin;
        //return null;
    }

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin){

        Optional<Dentist> dentistOptional = dentistRepository.findByUser(admin.getUser());
        if(!dentistOptional.isPresent()){
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);
        }
        return null;
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){
        return null;
    }
}
