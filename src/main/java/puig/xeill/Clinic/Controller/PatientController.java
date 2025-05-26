package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Patient;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.PatientRepository;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("patients")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepository;

    JwtUtil jwtUtil = new JwtUtil();

    @GetMapping("/show/{id}")
    public Optional<Patient> show(@PathVariable Long id) {

        System.out.println(id);

        Optional<Patient> patient = patientRepository.findById(id);
        System.out.println(new Date());

        if (patient.isPresent()) {

            try {
                patient.get().setDni(Security.decrypt(patient.get().getDni()));
                patient.get().setName(Security.decrypt(patient.get().getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return patient;
        }

        else return null;
        //return null;
    }

    @GetMapping("/get")
    public Page<Patient> getPatients (@RequestParam int page) {
        List<Patient> patientList = patientRepository.findAll();

        patientList.forEach(patient -> {
            try {
                patient.setDni(Security.decrypt(patient.getDni()));
                patient.setName(Security.decrypt(patient.getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), patientList.size());

        List<Patient> filterList = patientList.subList(start, end);

        Page<Patient> pagedResult = new PageImpl<>(filterList, pageable, patientList.size());
        return pagedResult;
    }

    @PostMapping("/create")
    public Patient create(@RequestBody Patient patient) throws Exception {

        Period edad = Period.between(patient.getBornDate(), LocalDate.now());
        if(edad.getYears() >= 18) {
            patient.setTutor(null);
        }

        patient.setName(Security.encrypt(patient.getName()));
        patient.setDni(Security.encrypt(patient.getDni()));

        patientRepository.save(patient);

        return null;
        //return null;
    }

    @PostMapping("/update")
    public Patient edit(@RequestBody Patient patient) throws Exception {
        try {
            // Buscar el paciente existente por ID
            Optional<Patient> existingPatientOpt = patientRepository.findById(patient.getId());
            
            if (existingPatientOpt.isPresent()) {
                Patient existingPatient = existingPatientOpt.get();
                
                // Actualizar los campos básicos sin encriptar primero
                existingPatient.setType(patient.getType());
                existingPatient.setBloodType(patient.getBloodType());
                
                // Encriptar los campos sensibles
                existingPatient.setName(Security.encrypt(patient.getName()));
                existingPatient.setDni(Security.encrypt(patient.getDni()));
                
                // Guardar los cambios
                Patient updatedPatient = patientRepository.save(existingPatient);
                
                // Crear objeto de respuesta con datos desencriptados
                Patient responsePatient = new Patient();
                responsePatient.setId(updatedPatient.getId());
                responsePatient.setName(Security.decrypt(updatedPatient.getName()));
                responsePatient.setDni(Security.decrypt(updatedPatient.getDni()));
                responsePatient.setType(updatedPatient.getType());
                responsePatient.setBloodType(updatedPatient.getBloodType());
                responsePatient.setBornDate(updatedPatient.getBornDate());
                
                return responsePatient;
            }
            
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el paciente: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Optional<Patient> patient = patientRepository.findById(id);
            if (patient.isPresent()) {
                patientRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el paciente: " + e.getMessage());
        }
    }
}