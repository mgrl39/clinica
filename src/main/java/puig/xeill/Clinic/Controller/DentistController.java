package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.util.List;


@RestController
@RequestMapping("dentists")
public class DentistController {

    @Autowired
    DentistRepository dentistRepository;

    JwtUtil jwtUtil = new JwtUtil();

    public Long edit(@RequestBody Dentist dentist) {
        return 0L;
    }


    @PostMapping("/create")
    public Long create(@RequestBody Dentist dentist) {

        System.out.println(dentist.toString());
        dentistRepository.save(dentist);

        return dentist.getId();
    }
    public List<Dentist> show() {
        return null;
    }
}
