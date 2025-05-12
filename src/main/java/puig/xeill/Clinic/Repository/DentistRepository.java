package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Persons.Dentist;

import java.util.Optional;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
    Optional<Dentist> findByUser(String name);
}
