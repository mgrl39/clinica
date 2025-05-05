package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Persons.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
