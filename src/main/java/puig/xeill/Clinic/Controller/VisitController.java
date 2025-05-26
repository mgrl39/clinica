package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.VisitDTO;
import puig.xeill.Clinic.Model.Enums.WeekDay;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Model.Request.VisitRequest;
import puig.xeill.Clinic.Model.Schedule;
import puig.xeill.Clinic.Model.Visit;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Repository.ScheduleRepository;
import puig.xeill.Clinic.Repository.VisitRepository;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("visits")
public class VisitController {
    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ScheduleRepository scheduleRepository;


    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/get")
    public List<VisitDTO> getVisits(@RequestHeader String token) throws Exception {
        String username = jwtUtil.getNameFromToken(token);
        Optional<Dentist> dentistOptional = dentistRepository.findByUser(username);
        if (dentistOptional.isEmpty()) {
            throw new Exception("Dentist not found");
        }
        Dentist dentist = dentistOptional.get();

        List<Visit> visits = visitRepository.findByIdDentist(dentist);

        List<VisitDTO> visitDTOList = visits.stream().map(visit -> {
            VisitDTO visitDTO = new VisitDTO();
            visitDTO.setId(visit.getId());
            visitDTO.setReason(visit.getReason().toString());
            visitDTO.setComment(visit.getComment());
            visitDTO.setDate(visit.getDate());
            visitDTO.setIdPatient(visit.getIdPatient().getId());
            visitDTO.setIdDentist(visit.getIdDentist().getId());
            return visitDTO;
        }).toList();

        return visitDTOList;
    }

    public String edit(@RequestBody Visit visit) {
        return null;
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody VisitRequest visitRequest, @RequestHeader String token) {
        Map<String, Object> response = new HashMap<>();
        try {
            String name = jwtUtil.getNameFromToken(token);
            Optional<Patient> patientOptional = patientRepository.findByDni(Security.encrypt(visitRequest.getDni()));
            Optional<Dentist> dentistOptional;

            if (visitRequest.getId_dentist() == null) {
                dentistOptional = dentistRepository.findByUser(name);
            } else {
                dentistOptional = dentistRepository.findById(visitRequest.getId_dentist());
            }

            if (dentistOptional.isEmpty() || patientOptional.isEmpty()) {
                response.put("success", false);
                response.put("message", "Paciente o dentista no encontrado.");
                return ResponseEntity.badRequest().body(response);
            }

            Dentist dentist = dentistOptional.get();
            Patient patient = patientOptional.get();

            Optional<Schedule> scheduleOptional = scheduleRepository.findById(dentist.getIdSchedule());
            if (scheduleOptional.isEmpty()) {
                response.put("success", false);
                response.put("message", "El dentista no tiene un horario asignado.");
                return ResponseEntity.badRequest().body(response);
            }
            Schedule schedule = scheduleOptional.get();

            WeekDay visitDay = WeekDay.fromDate(visitRequest.getDate());
            if (!isWithinWeekDayRange(visitDay, schedule.getFirstDay(), schedule.getLastDay())) {
                response.put("success", false);
                response.put("message", "La visita no está en un día válido según el horario del dentista.");
                return ResponseEntity.badRequest().body(response);
            }

            LocalTime firstHour = toLocalTime(schedule.getFirstHour());
            LocalTime lastHour = toLocalTime(schedule.getLastHour());

            LocalTime visitTime = visitRequest.getTime();

            if (visitTime.isBefore(firstHour) || visitTime.isAfter(lastHour)) {
                response.put("success", false);
                response.put("message", "La visita no está en un horario válido según el horario del dentista.");
                return ResponseEntity.badRequest().body(response);
            }

            Visit visit = new Visit();
            visit.setIdPatient(patient);
            visit.setIdDentist(dentist);
            visit.setDate(visitRequest.getDate());
            visit.setReason(visitRequest.getReason());
            visit.setComment(visitRequest.getComment());
            visit.setTime(visitTime);

            visitRepository.save(visit);

            response.put("success", true);
            response.put("message", "Visita creada correctamente.");
            response.put("data", visit.getId());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    private boolean isWithinWeekDayRange(WeekDay day, WeekDay start, WeekDay end) {
        int dayValue = day.ordinal();
        int startValue = start.ordinal();
        int endValue = end.ordinal();

        if (startValue <= endValue) {
            return dayValue >= startValue && dayValue <= endValue;
        } else {
            // rango que cruza el fin de semana (por ejemplo de viernes a lunes)
            return dayValue >= startValue || dayValue <= endValue;
        }
    }

    // Convierte Date a LocalTime
    private LocalTime toLocalTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }

}
