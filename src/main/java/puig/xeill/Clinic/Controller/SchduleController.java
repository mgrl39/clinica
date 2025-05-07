package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Repository.ScheduleRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("shedules")
public class SchduleController {

    @Autowired
    ScheduleRepository scheduleRepository;


    public String create(@RequestBody Schedule shedule){
        return "";
    }
    public String edit(@RequestBody Schedule schedule) {
        return "";
    }

    @GetMapping("/show/{id}")
    public Optional<Schedule> show(@PathVariable Long id) {

        System.out.println(id);
        Optional<Schedule> shcedule = scheduleRepository.findById(id);
        return shcedule;
        //return null;
    }
}
