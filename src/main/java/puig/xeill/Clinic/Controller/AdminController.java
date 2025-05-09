package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.ScheduleRepository;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("admins")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;


    @GetMapping("/show/{id}")
    public Optional<Admin> show(@PathVariable Long id) {

        System.out.println(id);
        Optional<Admin> admin = adminRepository.findById(id);
        System.out.println(new Date());
        return admin;
        //return null;
    }
}
