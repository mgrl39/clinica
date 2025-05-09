package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Repository.ScheduleRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("schedules")
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
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule;
    }
}
