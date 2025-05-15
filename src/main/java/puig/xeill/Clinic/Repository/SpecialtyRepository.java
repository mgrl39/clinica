package puig.xeill.Clinic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Model.Visit;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    //List<Specialty> findAllById(List<Long> ids);
}