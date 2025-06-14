package puig.xeill.Clinic.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Dentist;

import java.util.Optional;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
    Optional<Dentist> findByUser(String name);
}
