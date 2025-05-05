package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Persons.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
